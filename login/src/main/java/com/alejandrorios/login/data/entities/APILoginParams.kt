package com.alejandrorios.login.data.entities

/**
 * Created by Alejandro Rios on 2019-10-28
 */
data class APILoginParams(
    val userName: String,
    val password: String,
    val grandType: String = "password"
)
