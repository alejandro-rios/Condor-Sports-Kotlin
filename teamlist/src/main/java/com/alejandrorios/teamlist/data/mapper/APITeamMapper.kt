package com.alejandrorios.teamlist.data.mapper

import com.alejandrorios.core.models.TeamData
import com.alejandrorios.teamlist.data.entities.APITeamData

/**
 * Created by Alejandro Rios on 2019-10-24
 */
object APITeamMapper {

    fun map(apiTeamData: APITeamData): TeamData {
        return TeamData(
            apiTeamData.idTeam,
            apiTeamData.strTeam,
            apiTeamData.strAlternate,
            apiTeamData.intFormedYear,
            apiTeamData.strStadium,
            apiTeamData.strWebsite,
            apiTeamData.strFacebook,
            apiTeamData.strTwitter,
            apiTeamData.strInstagram,
            apiTeamData.strDescriptionEN,
            apiTeamData.strTeamBadge,
            apiTeamData.strTeamJersey,
            apiTeamData.strYoutube
        )
    }
}
