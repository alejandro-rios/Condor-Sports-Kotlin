package com.alejandrorios.login.data.repository

import com.alejandrorios.login.data.mapper.APITokenMapper
import com.alejandrorios.login.data.services.LoginService
import com.alejandrorios.login.domain.models.LoginParams
import com.alejandrorios.login.domain.models.TokenData
import com.alejandrorios.login.domain.repository.LoginRepository
import com.alejandrorios.login.utils.DEFAULT_GRANT_TYPE
import com.alejandrorios.login.utils.GRANT_TYPE
import com.alejandrorios.login.utils.PASSWORD
import com.alejandrorios.login.utils.USERNAME

/**
 * Created by Alejandro Rios on 2019-10-28
 */
class LoginRepositoryImpl(
    private val loginService: LoginService
) : LoginRepository {
    override suspend fun login(loginParams: LoginParams): TokenData {
        val loginParamsMap = mapOf(
            USERNAME to loginParams.userName,
            PASSWORD to loginParams.password,
            GRANT_TYPE to DEFAULT_GRANT_TYPE
        )

        val apiToken = loginService.login(loginParamsMap)

        return apiToken.let {
            APITokenMapper.map(it)
        }
    }
}
