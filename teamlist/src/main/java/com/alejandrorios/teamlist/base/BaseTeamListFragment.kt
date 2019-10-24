package com.alejandrorios.teamlist.base

import com.alejandrorios.core.contract.BaseFragment
import com.alejandrorios.core.di.CoreComponentProvider
import com.alejandrorios.teamlist.di.DaggerTeamListComponent
import com.alejandrorios.teamlist.di.TeamListComponent
import com.alejandrorios.teamlist.di.TeamListModule

/**
 * Created by Alejandro Rios on 2019-10-24
 */
abstract class BaseTeamListFragment : BaseFragment() {

    abstract fun injectFragmentBuilder(builder: TeamListComponent)

    override fun prepareFragmentBuilder() {
        injectFragmentBuilder(
            DaggerTeamListComponent.builder()
                .coreComponent((activity!!.application as CoreComponentProvider).getCoreComponent())
                .teamListModule(TeamListModule())
                .build()
        )
    }
}
