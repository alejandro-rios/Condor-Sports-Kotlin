package com.alejandrorios.core.repositories

import com.alejandrorios.core.models.TeamEventData

/**
 * Created by Alejandro Rios on 2019-10-26
 */
interface TeamEventsRepository {

    suspend fun getTeamEvents(teamId: String): List<TeamEventData>
}
