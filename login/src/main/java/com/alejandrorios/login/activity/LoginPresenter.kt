package com.alejandrorios.login.activity

import com.alejandrorios.core.CoroutineContextProvider
import com.alejandrorios.core.interactor.Interactor
import com.alejandrorios.core.models.TokenData
import com.alejandrorios.login.R
import com.alejandrorios.login.data.entities.APILoginParams
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

/**
 * Created by Alejandro Rios on 2019-10-28
 */
class LoginPresenter(
    private val loginInteractor: Interactor<TokenData, APILoginParams>,
    override val coroutinesContextProvider: CoroutineContextProvider
) : LoginContract.Presenter {

    override var view: LoginContract.View? = null
    override val parentJob: Job = Job()

    override fun doLogin(userName: String, password: String) {
        if (userName.isEmpty()) {
            view?.showError(R.string.login_username_error)
            return
        }

        if (password.isEmpty()) {
            view?.showError(R.string.login_password_empty)
            return
        }

        view?.showProgressDialog()
        launchJobOnMainDispatchers {
            try {
                withContext(coroutinesContextProvider.backgroundContext) {
                    val token = loginInteractor(APILoginParams(userName, password))
                }

                view?.onLoginSuccess()
            } catch (t: Throwable) {
                view?.hideProgressDialog()
                view?.showError(R.string.login_error)
            }
        }
    }
}
