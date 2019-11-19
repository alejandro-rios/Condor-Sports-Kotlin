package com.alejandrorios.core

import kotlin.coroutines.CoroutineContext

/**
 * Created by Alejandro Rios on 2019-10-23
 */
data class CoroutineContextProvider(
    val mainContext: CoroutineContext,
    val backgroundContext: CoroutineContext
)
