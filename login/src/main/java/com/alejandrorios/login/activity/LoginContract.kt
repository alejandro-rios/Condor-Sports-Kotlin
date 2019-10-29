package com.alejandrorios.login.activity

import com.alejandrorios.core.contract.BasePresenter
import com.alejandrorios.core.contract.BaseView

/**
 * Created by Alejandro Rios on 2019-10-28
 */
interface LoginContract {

    interface View : BaseView {

    }

    interface Presenter : BasePresenter<View> {

    }
}
