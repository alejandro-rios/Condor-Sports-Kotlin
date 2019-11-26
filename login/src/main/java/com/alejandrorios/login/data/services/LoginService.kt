package com.alejandrorios.login.data.services

import com.alejandrorios.login.data.entities.APIToken
import com.alejandrorios.login.utils.LOGIN_ENDPOINT
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Alejandro Rios on 2019-10-28
 */
interface LoginService {

    @FormUrlEncoded
    @POST(LOGIN_ENDPOINT)
    suspend fun login(@FieldMap apiLoginParams: Map<String, String>): APIToken
}
