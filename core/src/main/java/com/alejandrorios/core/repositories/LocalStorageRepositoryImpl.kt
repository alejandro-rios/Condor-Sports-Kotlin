package com.alejandrorios.core.repositories

import android.content.Context
import com.alejandrorios.core.constants.LocalStorageKey
import com.alejandrorios.core.constants.SharePreferenceGetter
import com.alejandrorios.core.exceptions.DataNoFoundOnLocalStorageException
import com.google.gson.Gson

/**
 * Created by Alejandro Rios on 2019-10-23
 */
class LocalStorageRepositoryImpl(
    private val context: Context,
    private val sharedPreferenceGetter: SharePreferenceGetter
) : LocalStorageRepository {

    private val gson = Gson()

    override fun <T> getData(key: LocalStorageKey, classType: Class<T>): T {
        val preference = sharedPreferenceGetter(context)
        val data = preference.getString(key, null) ?: throw DataNoFoundOnLocalStorageException(key)

        return gson.fromJson(data, classType)
    }

    override fun <T> saveData(key: LocalStorageKey, value: T) {
        val preferenceEditor = sharedPreferenceGetter(context).edit()
        preferenceEditor.putString(key, gson.toJson(value))
        preferenceEditor.apply()
    }
}
