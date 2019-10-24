package com.alejandrorios.core.mapper

import com.alejandrorios.core.models.TeamData
import com.alejandrorios.core.models.ViewTeamData

/**
 * Created by Alejandro Rios on 2019-10-23
 */
object ViewTeamsMapper {

    fun map(teamData: TeamData): ViewTeamData {
        return ViewTeamData(
            teamData.idTeam,
            teamData.strTeam,
            teamData.strAlternate,
            teamData.intFormedYear,
            teamData.strStadium,
            teamData.strWebsite,
            teamData.strFacebook,
            teamData.strTwitter,
            teamData.strInstagram,
            teamData.strDescriptionEN,
            teamData.strTeamBadge,
            teamData.strTeamJersey,
            teamData.strYoutube
        )
    }
}
