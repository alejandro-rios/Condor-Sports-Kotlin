package com.alejandrorios.teamlist.fragment

import com.alejandrorios.teamlist.domain.models.TeamData

/**
 * Created by Alejandro Rios on 2019-10-24
 */
interface TeamClickListener {

    fun onTeamClick(team: TeamData)
}
