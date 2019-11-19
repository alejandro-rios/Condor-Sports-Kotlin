package com.alejandrorios.teamdetails.di

import com.alejandrorios.core.di.CoreComponent
import com.alejandrorios.core.di.FeatureScope
import com.alejandrorios.teamdetails.activity.TeamDetailsActivity
import dagger.Component

/**
 * Created by Alejandro Rios on 2019-10-27
 */
@FeatureScope
@Component(
    modules = [TeamDetailsModule::class],
    dependencies = [CoreComponent::class]
)
interface TeamDetailsComponent {

    fun inject(teamDetailsActivity: TeamDetailsActivity)
}
