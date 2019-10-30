package com.alejandrorios.teamlist.fragment

import com.alejandrorios.core.CoroutineContextProvider
import com.alejandrorios.core.interactor.Interactor
import com.alejandrorios.teamlist.domain.mapper.ViewTeamsMapper
import com.alejandrorios.teamlist.domain.models.TeamData
import com.alejandrorios.teamlist.utils.SPANISH_LEAGUE_CODE
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

/**
 * Created by Alejandro Rios on 2019-10-24
 */
class TeamListPresenter(
    private val getTeamsInteractor: Interactor<List<TeamData>, String>,
    private val viewTeamMapper: ViewTeamsMapper,
    override val coroutinesContextProvider: CoroutineContextProvider
) : TeamListContract.Presenter {

    override var view: TeamListContract.View? = null
    override val parentJob: Job = Job()

    override fun onViewCreated() {
        getTeams(SPANISH_LEAGUE_CODE)
    }

    override fun getTeamsFromLeague(codeLeague: String) {
        getTeams(codeLeague)
    }

    private fun getTeams(codeLeague: String) {
        view?.showProgressDialog()
        launchJobOnMainDispatchers {
            try {
                val teams = withContext(coroutinesContextProvider.backgroundContext) {
                    getTeamsInteractor(codeLeague)
                }

                view?.showTeams(teams)
            } catch (t: Throwable) {
                view?.showError(t.message ?: "Something went wrong")
            } finally {
                view?.hideProgressDialog()
            }
        }
    }

    override fun onTeamClick(team: TeamData) {
        view?.openTeamDetails(viewTeamMapper.map(team))
    }
}
