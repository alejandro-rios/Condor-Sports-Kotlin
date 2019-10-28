package com.alejandrorios.core.repositories

import com.alejandrorios.core.models.LoginParams
import com.alejandrorios.core.models.TokenData

/**
 * Created by Alejandro Rios on 2019-10-28
 */
interface LoginRepository {

    suspend fun login(loginParams: LoginParams): TokenData
}
