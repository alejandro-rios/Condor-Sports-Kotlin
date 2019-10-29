package com.alejandrorios.teamlist.fragment

import com.alejandrorios.core.constants.StringResourceId
import com.alejandrorios.core.contract.BasePresenter
import com.alejandrorios.core.contract.BaseView
import com.alejandrorios.core.models.TeamData
import com.alejandrorios.core.models.ViewTeamData
import com.leinardi.android.speeddial.SpeedDialView

/**
 * Created by Alejandro Rios on 2019-10-24
 */
interface TeamListContract {

    interface View : BaseView, SpeedDialView.OnActionSelectedListener {

        fun showTeams(teams: List<TeamData>)

        fun showProgressDialog()

        fun hideProgressDialog()

        fun showError(error: StringResourceId)

        fun showError(error: String)

        fun openTeamDetails(viewTeam: ViewTeamData)
    }

    interface Presenter : BasePresenter<View>, TeamClickListener {
        fun onViewCreated()

        fun getTeamsFromLeague(codeLeague: String)
    }
}
