package com.alejandrorios.teamlist.di

import com.alejandrorios.core.di.CoreComponent
import com.alejandrorios.core.di.FeatureScope
import com.alejandrorios.teamlist.fragment.TeamListFragment
import dagger.Component

/**
 * Created by Alejandro Rios on 2019-10-24
 */
@FeatureScope
@Component(
    modules = [TeamListModule::class],
    dependencies = [CoreComponent::class]
)
interface TeamListComponent {

    fun inject(teamListFragment: TeamListFragment)

}
