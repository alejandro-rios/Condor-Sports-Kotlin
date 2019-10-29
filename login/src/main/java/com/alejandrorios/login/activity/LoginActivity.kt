package com.alejandrorios.login.activity

import android.os.Bundle
import com.airbnb.deeplinkdispatch.DeepLink
import com.alejandrorios.core.constants.TEAM_LOGIN_DEEP_LINK
import com.alejandrorios.login.R
import com.alejandrorios.login.base.BaseLoginActivity
import com.alejandrorios.login.di.LoginComponent
import javax.inject.Inject

/**
 * Created by Alejandro Rios on 2019-10-28
 */
@DeepLink(TEAM_LOGIN_DEEP_LINK)
class LoginActivity : BaseLoginActivity(), LoginContract.View {

    @Inject
    private lateinit var presenter: LoginContract.Presenter

    override fun injectActivityBuilder(builder: LoginComponent) {
        builder.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onResume() {
        super.onResume()
        presenter.bind(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unBind()
    }
}
