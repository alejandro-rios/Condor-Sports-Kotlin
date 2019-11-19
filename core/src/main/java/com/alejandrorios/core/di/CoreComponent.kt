package com.alejandrorios.core.di

import com.alejandrorios.core.CoroutineContextProvider
import com.alejandrorios.core.constants.BASE_RETROFIT
import com.alejandrorios.core.constants.COROUTINE_COMPUTATIONAL_CONTEXT_PROVIDER
import com.alejandrorios.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import com.alejandrorios.core.constants.MOBILE_RETROFIT
import com.alejandrorios.core.repositories.LocalStorageRepository
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Named

/**
 * Created by Alejandro Rios on 2019-10-23
 */
@Component(modules = [CoreModule::class, NetworkModule::class, RepositoryModule::class])
interface CoreComponent {

    @Named(COROUTINE_IO_CONTEXT_PROVIDER)
    fun provideIOCoroutineContextProvider(): CoroutineContextProvider

    @Named(COROUTINE_COMPUTATIONAL_CONTEXT_PROVIDER)
    fun provideComputationalCoroutineContextProvider(): CoroutineContextProvider

    @Named(BASE_RETROFIT)
    fun provideBaseRetrofit(): Retrofit

    @Named(MOBILE_RETROFIT)
    fun provideMobileRetrofit(): Retrofit

    fun provideLocalStorageRepository(): LocalStorageRepository
}
