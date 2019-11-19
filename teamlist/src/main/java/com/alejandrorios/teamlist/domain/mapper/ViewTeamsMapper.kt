package com.alejandrorios.teamlist.domain.mapper

import com.alejandrorios.core.viewmodels.ViewTeamData
import com.alejandrorios.teamlist.domain.models.TeamData

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
