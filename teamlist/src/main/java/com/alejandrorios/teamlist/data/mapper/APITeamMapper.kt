package com.alejandrorios.teamlist.data.mapper

import com.alejandrorios.teamlist.data.entities.APITeamData
import com.alejandrorios.teamlist.domain.models.TeamData

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
