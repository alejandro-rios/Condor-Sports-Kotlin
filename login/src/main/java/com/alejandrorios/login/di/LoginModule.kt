package com.alejandrorios.login.di

import com.alejandrorios.core.CoroutineContextProvider
import com.alejandrorios.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import com.alejandrorios.core.interactor.Interactor
import com.alejandrorios.core.models.TokenData
import com.alejandrorios.core.repositories.LoginRepository
import com.alejandrorios.login.activity.LoginContract
import com.alejandrorios.login.activity.LoginPresenter
import com.alejandrorios.login.data.entities.APILoginParams
import com.alejandrorios.login.data.mapper.APITokenMapper
import com.alejandrorios.login.data.repository.LoginRepositoryImpl
import com.alejandrorios.login.data.services.LoginService
import com.alejandrorios.login.domain.LoginInteractor
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

/**
 * Created by Alejandro Rios on 2019-10-28
 */
@Module
class LoginModule {

    @Provides
    fun provideLoginService(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }

    @Provides
    fun provideLoginRepository(loginService: LoginService): LoginRepository {
        return LoginRepositoryImpl(loginService, APITokenMapper)
    }

    @Provides
    fun provideLoginInterator(loginRepository: LoginRepository): Interactor<TokenData, APILoginParams> {
        return LoginInteractor(loginRepository)
    }

    @Provides
    fun provideLoginPresenter(
        loginInteractor: Interactor<TokenData, APILoginParams>,
        @Named(COROUTINE_IO_CONTEXT_PROVIDER) coroutineContextProvider: CoroutineContextProvider
    ): LoginContract.Presenter {
        return LoginPresenter(loginInteractor, coroutineContextProvider)
    }
}
