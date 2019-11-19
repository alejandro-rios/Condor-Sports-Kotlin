package com.alejandrorios.teamdetails

import com.alejandrorios.teamdetails.data.entities.APITeamEventData
import com.alejandrorios.teamdetails.data.entities.APITeamEvents
import com.alejandrorios.teamdetails.data.mapper.APITeamEventMapper
import com.alejandrorios.teamdetails.data.repository.TeamEventsRepositoryImpl
import com.alejandrorios.teamdetails.data.service.GetTeamEventsService
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

/**
 * Created by Alejandro Rios on 2019-10-26
 */
class TeamEventsRepositoryTest : MockableTest {

    @MockK
    lateinit var getTeamEventsService: GetTeamEventsService

    @Before
    override fun setup() {
        super.setup()

        coEvery {
            getTeamEventsService.getTeamEvents("133602")
        }.answers {
            APITeamEvents(
                listOf(
                    APITeamEventData(
                        "Liverpool",
                        "Tottenham",
                        "2019-10-27",
                        "133602",
                        "133616"
                    )
                )
            )
        }
    }

    @Test
    fun `should get team events`() {
        val repository = given {
            TeamEventsRepositoryImpl(getTeamEventsService, APITeamEventMapper)
        }

        val result = whenever {
            runBlocking {
                repository.getTeamEvents("133602")
            }
        }

        then {
            result[0].strHomeTeam shouldEqual "Liverpool"
            result[0].strAwayTeam shouldEqual "Tottenham"
            result[0].dateEvent shouldEqual "2019-10-27"
            result[0].idHomeTeam shouldEqual "133602"
            result[0].idAwayTeam shouldEqual "133616"
        }
    }
}
