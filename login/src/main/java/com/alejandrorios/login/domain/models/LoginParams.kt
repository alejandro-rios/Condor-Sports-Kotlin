package com.alejandrorios.login.domain.models

/**
 * Created by Alejandro Rios on 2019-10-28
 */
data class LoginParams(
    val userName: String,
    val password: String,
    val grandType: String = "password"
)
