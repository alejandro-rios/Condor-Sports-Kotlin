package com.alejandrorios.core.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Alejandro Rios on 2019-10-28
 */
data class TokenData(
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("expires_in")
    val expiresIn: Int
)
