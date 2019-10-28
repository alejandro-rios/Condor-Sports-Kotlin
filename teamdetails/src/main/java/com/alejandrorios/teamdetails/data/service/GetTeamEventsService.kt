package com.alejandrorios.teamdetails.data.service

import com.alejandrorios.teamdetails.data.entities.APITeamEvents
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Alejandro Rios on 2019-10-26
 */
interface GetTeamEventsService {

    @GET("eventsnext.php")
    suspend fun getTeamEvents(@Query("id") teamId: String): APITeamEvents
}
