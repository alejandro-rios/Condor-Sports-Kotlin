package com.alejandrorios.login.data.mapper

import com.alejandrorios.login.domain.models.TokenData
import com.alejandrorios.login.data.entities.APIToken

/**
 * Created by Alejandro Rios on 2019-10-28
 */
object APITokenMapper {

    fun map(apiToken: APIToken): TokenData {
        return TokenData(
            apiToken.tokenType,
            apiToken.refreshToken,
            apiToken.accessToken,
            apiToken.expiresIn
        )
    }
}
