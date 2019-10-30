package com.alejandrorios.login.domain.repository

import com.alejandrorios.login.domain.models.LoginParams
import com.alejandrorios.login.domain.models.TokenData

/**
 * Created by Alejandro Rios on 2019-10-28
 */
interface LoginRepository {

    suspend fun login(loginParams: LoginParams): TokenData
}
