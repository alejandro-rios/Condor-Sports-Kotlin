package com.alejandrorios.login.activity

import com.alejandrorios.core.CoroutineContextProvider
import com.alejandrorios.core.interactor.Interactor
import com.alejandrorios.core.models.TokenData
import com.alejandrorios.login.data.entities.APILoginParams
import kotlinx.coroutines.Job

/**
 * Created by Alejandro Rios on 2019-10-28
 */
class LoginPresenter(
    private val loginInteractor: Interactor<TokenData, APILoginParams>,
    override val coroutinesContextProvider: CoroutineContextProvider
) : LoginContract.Presenter {

    override var view: LoginContract.View? = null
    override val parentJob: Job = Job()


}
