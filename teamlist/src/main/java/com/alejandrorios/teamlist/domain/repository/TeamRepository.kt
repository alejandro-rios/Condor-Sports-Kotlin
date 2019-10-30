package com.alejandrorios.teamlist.domain.repository

import com.alejandrorios.teamlist.domain.models.TeamData

/**
 * Created by Alejandro Rios on 2019-10-29
 */
interface TeamRepository {

    suspend fun getTeamsList(codeLeague: String): List<TeamData>
}
