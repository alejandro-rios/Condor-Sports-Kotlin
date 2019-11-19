package com.alejandrorios.login.data.repository

import com.alejandrorios.login.domain.models.LoginParams
import com.alejandrorios.login.domain.models.TokenData
import com.alejandrorios.login.domain.repository.LoginRepository
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
        val loginParamsMap = mapOf(
            "username" to loginParams.userName,
            "password" to loginParams.password,
            "grant_type" to "password"
        )
        val apiToken = loginService.login(loginParamsMap)

        return apiToken.let {
            apiTokenMapper.map(it)
        }
    }
}
