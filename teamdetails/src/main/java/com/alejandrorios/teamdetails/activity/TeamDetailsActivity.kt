package com.alejandrorios.teamdetails.activity

import android.os.Bundle
import com.airbnb.deeplinkdispatch.DeepLink
import com.alejandrorios.core.constants.TEAM_DETAILS_DEEP_LINK
import com.alejandrorios.teamdetails.R
import com.alejandrorios.teamdetails.base.BaseTeamDetailsActivity
import com.alejandrorios.teamdetails.di.TeamDetailsComponent
import javax.inject.Inject

/**
 * Created by Alejandro Rios on 2019-10-27
 */
@DeepLink(TEAM_DETAILS_DEEP_LINK)
class TeamDetailsActivity : BaseTeamDetailsActivity(), TeamDetailsContract.View {

    @Inject
    lateinit var presenter: TeamDetailsContract.Presenter

    override fun injectActivityBuilder(builder: TeamDetailsComponent) {
        builder.inject(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.bind(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unBind()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_details)
    }
}
