package com.alejandrorios.login.activity

import com.alejandrorios.core.constants.StringResourceId
import com.alejandrorios.core.contract.BasePresenter
import com.alejandrorios.core.contract.BaseView

/**
 * Created by Alejandro Rios on 2019-10-28
 */
interface LoginContract {

    interface View : BaseView {
        fun onLoginSuccess()

        fun showProgressDialog()

        fun hideProgressDialog()

        fun showError(error: StringResourceId)

        fun showError(error: String)
    }

    interface Presenter : BasePresenter<View> {
        fun doLogin(userName: String, password: String)
    }
}
