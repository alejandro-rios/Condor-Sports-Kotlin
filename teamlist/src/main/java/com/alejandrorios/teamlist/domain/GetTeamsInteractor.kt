package com.alejandrorios.teamlist.domain

import com.alejandrorios.core.interactor.Interactor
import com.alejandrorios.core.models.TeamData
import com.alejandrorios.core.repositories.TeamRepository

/**
 * Created by Alejandro Rios on 2019-10-24
 */
class GetTeamsInteractor (private val teamRepository: TeamRepository):
    Interactor<List<TeamData>, String> {

    override suspend fun invoke(params: String): List<TeamData> {
        return teamRepository.getTeamsList(params)
    }
}
