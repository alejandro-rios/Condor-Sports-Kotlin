package com.alejandrorios.login.domain

import com.alejandrorios.core.interactor.Interactor
import com.alejandrorios.core.models.LoginParams
import com.alejandrorios.core.models.TokenData
import com.alejandrorios.core.repositories.LoginRepository
import com.alejandrorios.login.data.entities.APILoginParams

/**
 * Created by Alejandro Rios on 2019-10-28
 */
class LoginInteractor(private val loginRepository: LoginRepository) :
    Interactor<TokenData, APILoginParams> {

    override suspend fun invoke(params: APILoginParams): TokenData {
        return loginRepository.login(LoginParams(params.userName, params.password))
    }
}
