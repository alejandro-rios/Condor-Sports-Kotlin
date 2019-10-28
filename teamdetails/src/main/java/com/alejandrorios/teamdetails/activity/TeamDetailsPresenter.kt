package com.alejandrorios.teamdetails.activity

import com.alejandrorios.core.CoroutineContextProvider
import com.alejandrorios.core.interactor.Interactor
import com.alejandrorios.core.models.TeamEventData
import kotlinx.coroutines.Job

/**
 * Created by Alejandro Rios on 2019-10-27
 */
class TeamDetailsPresenter(
    private val getTeamEventsInteractor: Interactor<List<TeamEventData>, String>,
    override val coroutinesContextProvider: CoroutineContextProvider
) : TeamDetailsContract.Presenter{

    override var view: TeamDetailsContract.View? = null
    override val parentJob:Job = Job()
}
