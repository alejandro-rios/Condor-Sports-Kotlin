package com.alejandrorios.teamdetails.di

import com.alejandrorios.core.CoroutineContextProvider
import com.alejandrorios.core.constants.BASE_RETROFIT
import com.alejandrorios.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import com.alejandrorios.core.interactor.Interactor
import com.alejandrorios.teamdetails.domain.models.TeamEventData
import com.alejandrorios.teamdetails.domain.repository.TeamEventsRepository
import com.alejandrorios.teamdetails.activity.TeamDetailsContract
import com.alejandrorios.teamdetails.activity.TeamDetailsPresenter
import com.alejandrorios.teamdetails.data.mapper.APITeamEventMapper
import com.alejandrorios.teamdetails.data.repository.TeamEventsRepositoryImpl
import com.alejandrorios.teamdetails.data.service.GetTeamEventsService
import com.alejandrorios.teamdetails.domain.interactor.GetTeamEventsInteractor
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

/**
 * Created by Alejandro Rios on 2019-10-27
 */
@Module
class TeamDetailsModule {

    @Provides
    fun provideGetTeamDetailsService(@Named(BASE_RETROFIT) retrofit: Retrofit): GetTeamEventsService {
        return retrofit.create(GetTeamEventsService::class.java)
    }

    @Provides
    fun provideGetTeamDetailsRepository(getTeamEventsService: GetTeamEventsService): TeamEventsRepository {
        return TeamEventsRepositoryImpl(getTeamEventsService, APITeamEventMapper)
    }

    @Provides
    fun provideGetTeamDetailsInteractor(teamEventsRepository: TeamEventsRepository): Interactor<List<TeamEventData>, String> {
        return GetTeamEventsInteractor(
            teamEventsRepository
        )
    }

    @Provides
    fun provideTeamDetailsPresenter(
        getTeamEventsInteractor: Interactor<List<TeamEventData>, String>,
        @Named(COROUTINE_IO_CONTEXT_PROVIDER) coroutineContextProvider: CoroutineContextProvider
    ): TeamDetailsContract.Presenter {
        return TeamDetailsPresenter(getTeamEventsInteractor, coroutineContextProvider)
    }
}
