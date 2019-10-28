package com.alejandrorios.teamlist.data.repository

import com.alejandrorios.core.models.TeamData
import com.alejandrorios.core.repositories.TeamRepository
import com.alejandrorios.teamlist.data.mapper.APITeamMapper
import com.alejandrorios.teamlist.data.service.GetTeamService

/**
 * Created by Alejandro Rios on 2019-10-24
 */
class TeamRepositoryImpl(
    private val getTeamService: GetTeamService,
    private val apiTeamMapper: APITeamMapper
) : TeamRepository {

    override suspend fun getTeamsList(codeLeague: String): List<TeamData> {
        val apiTeams = getTeamService.getTeams(codeLeague)

        return apiTeams.teams!!.map { apiTeamData ->
            apiTeamMapper.map(apiTeamData)
        }
    }
}
