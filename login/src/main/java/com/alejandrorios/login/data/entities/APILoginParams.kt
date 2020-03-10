package com.alejandrorios.login.data.entities

import com.alejandrorios.login.utils.DEFAULT_GRANT_TYPE

/**
 * Created by Alejandro Rios on 2019-10-28
 */
data class APILoginParams(
    val userName: String,
    val password: String,
    val grantType: String = DEFAULT_GRANT_TYPE
)
