package com.alejandrorios.core.di

import android.content.Context
import com.alejandrorios.core.CoroutineContextProvider
import com.alejandrorios.core.constants.BASE_URL
import com.alejandrorios.core.constants.COROUTINE_COMPUTATIONAL_CONTEXT_PROVIDER
import com.alejandrorios.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

/**
 * Created by Alejandro Rios on 2019-10-23
 */
@Module
class CoreModule(private val appContext: Context) {

    @Provides
    fun provideAppContext(): Context {
        return appContext
    }

    @Named(COROUTINE_IO_CONTEXT_PROVIDER)
    @Provides
    fun provideIOCoroutineContextProvider(): CoroutineContextProvider {
        return CoroutineContextProvider(Dispatchers.Main, Dispatchers.IO)
    }

    @Named(COROUTINE_COMPUTATIONAL_CONTEXT_PROVIDER)
    @Provides
    fun provideComputationCoroutineContextProvider(): CoroutineContextProvider {
        return CoroutineContextProvider(Dispatchers.Main, Dispatchers.Default)
    }

    @Provides
    fun provideBaseUrl(): String {
        return BASE_URL
    }
}
