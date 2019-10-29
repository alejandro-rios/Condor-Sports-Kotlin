package com.alejandrorios.login.di

import com.alejandrorios.core.di.CoreComponent
import com.alejandrorios.core.di.FeatureScope
import com.alejandrorios.login.activity.LoginActivity
import dagger.Component

/**
 * Created by Alejandro Rios on 2019-10-28
 */
@FeatureScope
@Component(
    modules = [LoginModule::class],
    dependencies = [CoreComponent::class]
)
interface LoginComponent {

    fun inject(loginActivity: LoginActivity)

}
