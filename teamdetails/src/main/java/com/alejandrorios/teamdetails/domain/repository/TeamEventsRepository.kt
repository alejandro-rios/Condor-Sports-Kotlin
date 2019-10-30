package com.alejandrorios.teamdetails.domain.repository

import com.alejandrorios.teamdetails.domain.models.TeamEventData

/**
 * Created by Alejandro Rios on 2019-10-26
 */
interface TeamEventsRepository {

    suspend fun getTeamEvents(teamId: String): List<com.alejandrorios.teamdetails.domain.models.TeamEventData>
}
