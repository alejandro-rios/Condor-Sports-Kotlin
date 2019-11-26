package com.alejandrorios.login.interactor

import com.alejandrorios.login.MockableTest
import com.alejandrorios.login.data.entities.APILoginParams
import com.alejandrorios.login.domain.interactor.LoginInteractor
import com.alejandrorios.login.domain.models.LoginParams
import com.alejandrorios.login.domain.models.TokenData
import com.alejandrorios.login.domain.repository.LoginRepository
import com.alejandrorios.login.given
import com.alejandrorios.login.then
import com.alejandrorios.login.whenever
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by alejandrorios on 2019-11-25
 */
class LoginInteractorTest : MockableTest {

    @MockK
    lateinit var loginRepository: LoginRepository
    @MockK
    lateinit var failLoginRepository: LoginRepository

    @Before
    override fun setup() {
        super.setup()

        coEvery {
            loginRepository.login(any())
        }.answers {
            TokenData(
                "fff",
                "ddd",
                "dddd",
                2222
            )
        }

        coEvery {
            failLoginRepository.login(any())
        }.answers {
            throw Throwable()
        }
    }

    @Test
    fun `should do login`() {
        val interactor = given {
            LoginInteractor(loginRepository)
        }

        val result = whenever {
            runBlocking {
                interactor(APILoginParams("RN999", "sss"))
            }
        }

        then {
            result.tokenType shouldEqual "fff"
            result.refreshToken shouldEqual "ddd"
            result.accessToken shouldEqual "dddd"
            result.expiresIn shouldEqual 2222

            coVerify {
                loginRepository.login(LoginParams("RN999", "sss"))
            }
        }
    }

    @Test(expected = Throwable::class)
    fun `should fail login`(){
        val interactor = given {
            LoginInteractor(failLoginRepository)
        }

        val result = whenever {
            runBlocking {
                interactor(APILoginParams("RN999", "sss"))
            }
        }

        then {
            Assert.assertEquals(Throwable(), result)

            coVerify {
                failLoginRepository.login(LoginParams("RN999", "sss"))
            }
        }
    }
}
