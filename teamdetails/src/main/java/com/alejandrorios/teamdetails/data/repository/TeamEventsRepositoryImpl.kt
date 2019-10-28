package com.alejandrorios.teamdetails.data.repository

import com.alejandrorios.core.models.TeamEventData
import com.alejandrorios.core.repositories.TeamEventsRepository
import com.alejandrorios.teamdetails.data.mapper.APITeamEventMapper
import com.alejandrorios.teamdetails.data.service.GetTeamEventsService

/**
 * Created by Alejandro Rios on 2019-10-26
 */
class TeamEventsRepositoryImpl(
    private val getTeamEventsService: GetTeamEventsService,
    private val apiTeamEventMapper: APITeamEventMapper
) : TeamEventsRepository {

    override suspend fun getTeamEvents(teamId: String): List<TeamEventData> {
        val apiTeamEvents = getTeamEventsService.getTeamEvents(teamId)

        return apiTeamEvents.events!!.map { apiEventsData ->
            apiTeamEventMapper.map(apiEventsData)
        }
    }
}
