package com.alejandrorios.teamdetails.activity

import com.alejandrorios.core.CoroutineContextProvider
import com.alejandrorios.core.interactor.Interactor
import com.alejandrorios.teamdetails.domain.models.TeamEventData
import com.alejandrorios.core.viewmodels.ViewTeamData
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

/**
 * Created by Alejandro Rios on 2019-10-27
 */
class TeamDetailsPresenter(
    private val getTeamEventsInteractor: Interactor<List<TeamEventData>, String>,
    override val coroutinesContextProvider: CoroutineContextProvider
) : TeamDetailsContract.Presenter {

    override var view: TeamDetailsContract.View? = null
    override val parentJob: Job = Job()

    override fun getTeamEvents(team: ViewTeamData) {
        view?.showProgressDialog()
        launchJobOnMainDispatchers {
            try {
                val teamEvents = withContext(coroutinesContextProvider.backgroundContext) {
                    team.idTeam?.let { getTeamEventsInteractor(it) }
                }

                teamEvents?.let {
                    view?.showTeamEvents(it)
                }
            } catch (t: Throwable) {
                view?.showError(t.message ?: "Something went wrong")
            } finally {
                view?.hideProgressDialog()
            }
        }
    }
}
