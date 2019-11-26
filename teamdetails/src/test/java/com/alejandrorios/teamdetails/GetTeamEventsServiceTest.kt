package com.alejandrorios.teamdetails

import com.alejandrorios.core.di.NetworkModule
import com.alejandrorios.teamdetails.data.entities.APITeamEventData
import com.alejandrorios.teamdetails.data.service.GetTeamEventsService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

/**
 * Created by Alejandro Rios on 2019-10-26
 */
class GetTeamEventsServiceTest : MockServerTest {

    override lateinit var mockServer: MockWebServer

    private val networkModule = NetworkModule()

    @Before
    override fun setUp() {
        super.setUp()
        mockServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(
                    "{\n " +
                    "  \"events\": [\n" +
                    "    {\n" +
                    "      \"strHomeTeam\": \"Liverpool\",\n" +
                    "      \"strAwayTeam\": \"Tottenham\",\n" +
                    "      \"dateEvent\": \"2019-10-27\",\n" +
                    "      \"idHomeTeam\": \"133602\",\n" +
                    "      \"idAwayTeam\": \"133616\" \n" +
                    "    }\n" +
                    "]\n" +
                    "}"
                )
        )
    }

    @Test
    fun `should get team events`(){
        val service = given {
            networkModule.provideBaseRetrofit(
                mockServer.url(" ").toString(),
                networkModule.providerGsonConverter(),
                networkModule.provideHttpClient(networkModule.provideLoggingInterceptor())
            ).create(GetTeamEventsService::class.java)
        }

        val result = whenever {
            runBlocking { service.getTeamEvents("133602").events }
        }

        then {
            result!![0] shouldEqual APITeamEventData(
                "Liverpool",
                "Tottenham",
                "2019-10-27",
                "133602",
                "133616"
            )
        }
    }
}
