package com.alejandrorios.teamdetails.activity

import com.alejandrorios.core.contract.BasePresenter
import com.alejandrorios.core.contract.BaseView

/**
 * Created by Alejandro Rios on 2019-10-27
 */
interface TeamDetailsContract {

    interface View : BaseView {

    }

    interface Presenter : BasePresenter<View> {

    }
}
