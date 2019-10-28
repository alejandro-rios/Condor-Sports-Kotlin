package com.alejandrorios.teamdetails.base

import com.alejandrorios.core.contract.BaseActivity
import com.alejandrorios.core.di.CoreComponentProvider
import com.alejandrorios.teamdetails.di.TeamDetailsComponent
import com.alejandrorios.teamdetails.di.TeamDetailsModule

/**
 * Created by Alejandro Rios on 2019-10-27
 */
abstract class BaseTeamDetailsActivity : BaseActivity() {

    abstract fun injectActivityBuilder(builder: TeamDetailsComponent)

    override fun prepareActivityBuilder() {
        injectActivityBuilder(
            DaggerTeamDetailsComponent.builder()
                .coreComponent((activity!!.application as CoreComponentProvider).getCoreComponent())
                .teamDetailsModule(TeamDetailsModule())
                .build()
        )
    }
}
