package com.alejandrorios.core

import android.content.Context
import android.content.SharedPreferences
import com.alejandrorios.core.exceptions.DataNoFoundOnLocalStorageException
import com.alejandrorios.core.repositories.LocalStorageRepositoryImpl
import com.google.gson.Gson
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

/**
 * Created by Alejandro Rios on 2019-10-23
 */
class LocalStorageImpTest : MockableTest {

    @MockK
    lateinit var context: Context

    @MockK
    lateinit var sharedPreferences: SharedPreferences

    @MockK
    lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    @Before
    override fun setup() {
        super.setup()

        every {
            sharedPreferences.getString("myStringKey", null)
        }.answers {
            "pepito"
        }

        every {
            sharedPreferences.getString("myIntKey", null)
        }.answers {
            "10"
        }

        every {
            sharedPreferences.getString("myLongKey", null)
        }.answers {
            "11"
        }

        every {
            sharedPreferences.getString("myDoubleKey", null)
        }.answers {
            "12"
        }

        every {
            sharedPreferences.getString("myObjectKey", null)
        }.answers {
            Gson().toJson(SomeObject("1", 2))
        }

        every {
            sharedPreferences.edit()
        }.answers {
            sharedPreferencesEditor
        }

        every {
            sharedPreferencesEditor.putString(any(), any())
        }.answers {
            sharedPreferencesEditor
        }

        every {
            sharedPreferencesEditor.apply()
        }.answers {
            Unit
        }

        every {
            sharedPreferences.getString("myMissingKey", null)
        }.answers {
            null
        }
    }

    @Test
    fun `should get an string`() {
        val repository = given {
            LocalStorageRepositoryImpl(context) {
                sharedPreferences
            }
        }

        val result = whenever {
            repository.getData("myStringKey", String::class.java)
        }

        then {
            result shouldEqual "pepito"
        }
    }

    @Test
    fun `should get an int`() {
        val repository = given {
            LocalStorageRepositoryImpl(context) {
                sharedPreferences
            }
        }

        val result = whenever {
            repository.getData("myIntKey", Int::class.java)
        }

        then {
            result shouldEqual 10
        }
    }

    @Test
    fun `should get an long`() {
        val repository = given {
            LocalStorageRepositoryImpl(context) {
                sharedPreferences
            }
        }

        val result = whenever {
            repository.getData("myLongKey", Long::class.java)
        }

        then {
            result shouldEqual 11.toLong()
        }
    }

    @Test
    fun `should get an double`() {
        val repository = given {
            LocalStorageRepositoryImpl(context) {
                sharedPreferences
            }
        }

        val result = whenever {
            repository.getData("myDoubleKey", Double::class.java)
        }

        then {
            result shouldEqual 12.toDouble()
        }
    }

    @Test
    fun `should get an object`() {
        val repository = given {
            LocalStorageRepositoryImpl(context) {
                sharedPreferences
            }
        }

        val result = whenever {
            repository.getData("myObjectKey", SomeObject::class.java)
        }

        then {
            result shouldEqual SomeObject("1", 2)
        }
    }

    @Test
    fun `should get an value`() {
        val repository = given {
            LocalStorageRepositoryImpl(context) {
                sharedPreferences
            }
        }

        whenever {
            repository.saveData("myObjectKey", 123)
        }

        then {
            verify {
                sharedPreferencesEditor.putString("myObjectKey", "123")
            }
        }
    }

    @Test(expected = DataNoFoundOnLocalStorageException::class)
    fun `should get an exception if no data is found`() {
        val repository = given {
            LocalStorageRepositoryImpl(context) {
                sharedPreferences
            }
        }

        whenever {
            repository.getData("myMissingKey", String::class.java)
        }
    }
}
