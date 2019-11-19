package com.alejandrorios.teamlist.domain.interactor

import com.alejandrorios.core.interactor.Interactor
import com.alejandrorios.teamlist.data.entities.APITeamData
import com.alejandrorios.teamlist.domain.models.TeamData
import com.alejandrorios.teamlist.domain.repository.TeamRepository

/**
 * Created by Alejandro Rios on 2019-10-24
 */
class GetTeamsInteractor(private val teamRepository: TeamRepository) :
    Interactor<List<TeamData>, String> {

    override suspend fun invoke(params: String): List<TeamData> {
        return teamRepository.getTeamsList(params)
    }
}
