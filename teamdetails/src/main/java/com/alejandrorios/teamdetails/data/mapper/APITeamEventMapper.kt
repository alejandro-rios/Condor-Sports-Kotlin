package com.alejandrorios.teamdetails.data.mapper

import com.alejandrorios.teamdetails.domain.models.TeamEventData
import com.alejandrorios.teamdetails.data.entities.APITeamEventData

/**
 * Created by Alejandro Rios on 2019-10-26
 */
object APITeamEventMapper {

    fun map(apiTeamEventData: APITeamEventData): TeamEventData {
        return TeamEventData(
            apiTeamEventData.strHomeTeam,
            apiTeamEventData.strAwayTeam,
            apiTeamEventData.dateEvent,
            apiTeamEventData.idHomeTeam,
            apiTeamEventData.idAwayTeam
        )
    }
}
