package com.alejandrorios.core.repositories

import com.alejandrorios.core.constants.LocalStorageKey

/**
 * Created by Alejandro Rios on 2019-10-23
 */
interface LocalStorageRepository {

    fun <T> saveData(key: LocalStorageKey, value: T)

    fun <T> getData(key: LocalStorageKey, classType: Class<T>): T
}
