package com.alejandrorios.teamlist.data.repository

import com.alejandrorios.teamlist.data.mapper.APITeamMapper
import com.alejandrorios.teamlist.data.service.GetTeamService
import com.alejandrorios.teamlist.domain.models.TeamData
import com.alejandrorios.teamlist.domain.repository.TeamRepository

/**
 * Created by Alejandro Rios on 2019-10-24
 */
class TeamRepositoryImpl(
    private val getTeamService: GetTeamService,
    private val apiTeamMapper: APITeamMapper
) : TeamRepository {

    override suspend fun getTeamsList(codeLeague: String): List<TeamData> {
        val apiTeamsResult = getTeamService.getTeams(codeLeague)

        return apiTeamsResult.teams!!.map { apiTeams ->
            apiTeamMapper.map(apiTeams)
        }
    }
}
