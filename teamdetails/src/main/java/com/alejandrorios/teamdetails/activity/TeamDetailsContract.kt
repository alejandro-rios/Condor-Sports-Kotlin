package com.alejandrorios.teamdetails.activity

import com.alejandrorios.core.constants.StringResourceId
import com.alejandrorios.core.contract.BasePresenter
import com.alejandrorios.core.contract.BaseView
import com.alejandrorios.core.models.TeamEventData
import com.alejandrorios.core.models.ViewTeamData
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * Created by Alejandro Rios on 2019-10-27
 */
interface TeamDetailsContract {

    interface View : BaseView, BottomNavigationView.OnNavigationItemSelectedListener {

        fun showTeamEvents(events: List<TeamEventData>)

        fun showProgressDialog()

        fun hideProgressDialog()

        fun showError(error: StringResourceId)

        fun showError(error: String)
    }

    interface Presenter : BasePresenter<View> {

        fun getTeamEvents(team: ViewTeamData)
    }
}
