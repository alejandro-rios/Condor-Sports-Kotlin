package com.alejandrorios.teamlist.data.service

import com.alejandrorios.teamlist.data.entities.APITeamData
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Alejandro Rios on 2019-10-24
 */
interface GetTeamService {

    @GET("lookup_all_teams.php")
    suspend fun getTeams(@Query("id") codeLeague: String): List<APITeamData>
}
