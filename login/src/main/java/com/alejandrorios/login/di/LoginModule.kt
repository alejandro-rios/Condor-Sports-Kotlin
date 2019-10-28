package com.alejandrorios.login.di

import com.alejandrorios.core.repositories.LoginRepository
import com.alejandrorios.login.data.mapper.APITokenMapper
import com.alejandrorios.login.data.repository.LoginRepositoryImpl
import com.alejandrorios.login.data.services.LoginService
import com.alejandrorios.login.domain.LoginInteractor
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

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


}
