package com.alejandrorios.login.domain.interactor

import com.alejandrorios.core.interactor.Interactor
import com.alejandrorios.login.data.entities.APILoginParams
import com.alejandrorios.login.domain.models.LoginParams
import com.alejandrorios.login.domain.models.TokenData
import com.alejandrorios.login.domain.repository.LoginRepository

/**
 * Created by Alejandro Rios on 2019-10-28
 */
class LoginInteractor(private val loginRepository: LoginRepository) :
    Interactor<TokenData, APILoginParams> {

    override suspend fun invoke(params: APILoginParams): TokenData {
        return loginRepository.login(
            LoginParams(
                params.userName,
                params.password
            )
        )
    }
}
