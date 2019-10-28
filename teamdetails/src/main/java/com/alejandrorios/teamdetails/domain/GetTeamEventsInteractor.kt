package com.alejandrorios.teamdetails.domain

import com.alejandrorios.core.interactor.Interactor
import com.alejandrorios.core.models.TeamEventData
import com.alejandrorios.core.repositories.TeamEventsRepository

/**
 * Created by Alejandro Rios on 2019-10-27
 */
class GetTeamEventsInteractor(private val teamEventsRepository: TeamEventsRepository) :
    Interactor<List<TeamEventData>, String> {

    override suspend fun invoke(params: String): List<TeamEventData> {
        return teamEventsRepository.getTeamEvents(params)
    }
}
