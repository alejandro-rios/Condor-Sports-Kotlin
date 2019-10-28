package com.alejandrorios.login.data.services

import com.alejandrorios.login.data.entities.APILoginParams
import com.alejandrorios.login.data.entities.APIToken
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Alejandro Rios on 2019-10-28
 */
interface LoginService {

    @POST("oauth2/token")
    suspend fun login(@Body apiLoginParams: APILoginParams): APIToken
}
