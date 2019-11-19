package com.alejandrorios.core.interactor

/**
 * Created by Alejandro Rios on 2019-10-24
 */
interface Interactor<Response, Params> where Response : Any {

    suspend operator fun invoke(params: Params): Response
}
