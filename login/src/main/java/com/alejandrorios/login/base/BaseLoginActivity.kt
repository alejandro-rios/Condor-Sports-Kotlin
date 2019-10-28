package com.alejandrorios.login.base

import com.alejandrorios.core.contract.BaseActivity
import com.alejandrorios.core.di.CoreComponentProvider
import com.alejandrorios.login.di.LoginComponent
import com.alejandrorios.login.di.LoginModule

/**
 * Created by Alejandro Rios on 2019-10-28
 */
abstract class BaseLoginActivity : BaseActivity() {

    abstract fun injectActivityBuilder(builder: LoginComponent)

    override fun prepareActivityBuilder() {
        injectActivityBuilder(
            DaggerLoginComponent.builder()
                .coreComponent((application as CoreComponentProvider).getCoreComponent())
                .LoginModule(LoginModule())
                .build()
        )
    }
}
