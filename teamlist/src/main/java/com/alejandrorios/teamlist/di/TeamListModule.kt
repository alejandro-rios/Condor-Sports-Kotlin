package com.alejandrorios.teamlist.di

import com.alejandrorios.core.CoroutineContextProvider
import com.alejandrorios.core.constants.BASE_RETROFIT
import com.alejandrorios.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import com.alejandrorios.core.interactor.Interactor
import com.alejandrorios.teamlist.data.mapper.APITeamMapper
import com.alejandrorios.teamlist.data.repository.TeamRepositoryImpl
import com.alejandrorios.teamlist.data.service.GetTeamService
import com.alejandrorios.teamlist.domain.interactor.GetTeamsInteractor
import com.alejandrorios.teamlist.domain.mapper.ViewTeamsMapper
import com.alejandrorios.teamlist.domain.models.TeamData
import com.alejandrorios.teamlist.domain.repository.TeamRepository
import com.alejandrorios.teamlist.fragment.TeamListContract
import com.alejandrorios.teamlist.fragment.TeamListPresenter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

/**
 * Created by Alejandro Rios on 2019-10-24
 */
@Module
class TeamListModule {

    @Provides
    fun provideGetTeamsListService(@Named(BASE_RETROFIT) retrofit: Retrofit): GetTeamService {
        return retrofit.create(GetTeamService::class.java)
    }

    @Provides
    fun provideGetTeamsRepository(getTeamService: GetTeamService): TeamRepository {
        return TeamRepositoryImpl(getTeamService, APITeamMapper)
    }

    @Provides
    fun provideGetTeamsInteractor(teamRepository: TeamRepository): Interactor<List<TeamData>, String> {
        return GetTeamsInteractor(teamRepository)
    }

    @Provides
    fun provideTeamListPresenter(
        getTeamListInteractor: Interactor<List<TeamData>, String>,
        @Named(COROUTINE_IO_CONTEXT_PROVIDER) coroutineContextProvider: CoroutineContextProvider
    ): TeamListContract.Presenter {
        return TeamListPresenter(
            getTeamListInteractor,
            ViewTeamsMapper, coroutineContextProvider
        )
    }
}
