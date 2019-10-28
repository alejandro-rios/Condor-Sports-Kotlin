package com.alejandrorios.login.data.repository

import com.alejandrorios.core.models.LoginParams
import com.alejandrorios.core.models.TokenData
import com.alejandrorios.core.repositories.LoginRepository
import com.alejandrorios.login.data.entities.APILoginParams
import com.alejandrorios.login.data.mapper.APITokenMapper
import com.alejandrorios.login.data.services.LoginService

/**
 * Created by Alejandro Rios on 2019-10-28
 */
class LoginRepositoryImpl(
    private val loginService: LoginService,
    private val apiTokenMapper: APITokenMapper
) : LoginRepository {
    override suspend fun login(loginParams: LoginParams): TokenData {
        val apiToken = loginService.login(APILoginParams(loginParams.userName, loginParams.password ))

        return apiToken.let {
            apiTokenMapper.map(it)
        }
    }
}
