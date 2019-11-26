package com.alejandrorios.login.domain.models

import com.alejandrorios.login.utils.DEFAULT_GRANT_TYPE

/**
 * Created by Alejandro Rios on 2019-10-28
 */
data class LoginParams(
    val userName: String,
    val password: String,
    val grantType: String? = DEFAULT_GRANT_TYPE
)
