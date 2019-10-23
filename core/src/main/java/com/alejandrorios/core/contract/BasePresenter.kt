package com.alejandrorios.core.contract

import com.alejandrorios.core.CoroutineContextProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

/**
 * Created by Alejandro Rios on 2019-10-23
 */
interface BasePresenter<View : BaseView> {

    var view: View?
    val parentJob: Job
    val coroutinesContextProvider: CoroutineContextProvider

    fun bind(view: View) {
        this.view = view
    }

    fun unBind() {
        view = null
        parentJob.apply {
            cancelChildren()
        }
    }

    fun launchJobOnMainDispatchers(job: suspend CoroutineScope.() -> Unit) {
        CoroutineScope(coroutinesContextProvider.mainContext + parentJob).launch {
            job()
        }
    }
}
