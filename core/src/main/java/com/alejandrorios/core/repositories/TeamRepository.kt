package com.alejandrorios.core.repositories

import com.alejandrorios.core.models.TeamData

/**
 * Created by Alejandro Rios on 2019-10-23
 */
interface TeamRepository {

    suspend fun getTeamsList(codeLeague: String): List<TeamData>
}
