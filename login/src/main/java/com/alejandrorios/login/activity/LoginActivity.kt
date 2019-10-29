package com.alejandrorios.login.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.airbnb.deeplinkdispatch.DeepLink
import com.alejandrorios.core.constants.StringResourceId
import com.alejandrorios.core.constants.TEAM_LIST_DEEP_LINK
import com.alejandrorios.core.constants.TEAM_LOGIN_DEEP_LINK
import com.alejandrorios.core.extensions.startDeepLinkIntent
import com.alejandrorios.login.R
import com.alejandrorios.login.base.BaseLoginActivity
import com.alejandrorios.login.di.LoginComponent
import com.alejandrorios.login.utils.ANIMATION_TIME
import com.alejandrorios.login.utils.ONE_ALPHA
import com.alejandrorios.login.utils.ZERO_ALPHA
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

/**
 * Created by Alejandro Rios on 2019-10-28
 */
@DeepLink(TEAM_LOGIN_DEEP_LINK)
class LoginActivity : BaseLoginActivity(), LoginContract.View {

    @Inject
    lateinit var presenter: LoginContract.Presenter

    private val shortAnimTime: Long = ANIMATION_TIME

    override fun injectActivityBuilder(builder: LoginComponent) {
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
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            val userName = edUserName?.text?.toString() ?: return@setOnClickListener
            val password = edPassword?.text?.toString() ?: return@setOnClickListener

            presenter.doLogin(userName, password)
        }
    }

    override fun onLoginSuccess() {
        startDeepLinkIntent(TEAM_LIST_DEEP_LINK)
    }

    override fun showProgressDialog() {
        lytLogin.visibility = View.GONE
        pbLogin.visibility = View.VISIBLE
        pbLogin.animate().setDuration(shortAnimTime).alpha((ONE_ALPHA))
            .setListener(object :
                AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    pbLogin.visibility = View.VISIBLE
                }
            })
    }

    override fun hideProgressDialog() {
        pbLogin.visibility = View.GONE
        lytLogin.visibility = View.VISIBLE
        pbLogin.animate().setDuration(shortAnimTime).alpha((ZERO_ALPHA))
            .setListener(object :
                AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    pbLogin.visibility = View.GONE
                }
            })
    }

    override fun showError(error: StringResourceId) {
        showError(getString(error))
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }
}
