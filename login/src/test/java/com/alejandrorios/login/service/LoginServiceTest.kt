package com.alejandrorios.login.service

import com.alejandrorios.core.di.NetworkModule
import com.alejandrorios.login.MockServerTest
import com.alejandrorios.login.data.entities.APIToken
import com.alejandrorios.login.data.services.LoginService
import com.alejandrorios.login.given
import com.alejandrorios.login.then
import com.alejandrorios.login.whenever
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

/**
 * Created by Alejandro Rios on 2019-10-28
 */
class LoginServiceTest : MockServerTest {

    override lateinit var mockWebServer: MockWebServer

    private val networkModule = NetworkModule()
    private val apiLoginParamsMap = mapOf(
        "username" to "RN99999999",
        "password" to "password",
        "grant_type" to "password"
    )

    @Before
    override fun setUp() {
        super.setUp()
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(
                    "{\n" +
                            "    \"token_type\": \"bearer\",\n" +
                            "    \"refresh_token\": \"DxnbRUMpVMZyoragxka3zaRVQO-hTUfzTnXKPdB-iUr_Z3_Qo8f7z6Wd4UQgZ11GRmBCZ5YT6DfulXsFZeie1otzV5_u5Tpq-n_jThT_ci9__QI07sPG1ziXlM_XRjGJNdWszJFrzC1Pa-r5g-d3Ha8CP2dHP8mj3cpQBJxXrT9CPExJHQnFeSTOhMGl3Sn0RPNV4I-1uhnXXRsrNATp0dRluJIwln6Z1JShEEe6L15Kx14UpiUgmFi4W32MrREmDOi7jvwXgkDuBGvTtzAHJYesj6ldaPn5CSX59VCT-hajXH3SOEptNKo2taFH1N22XZ86IZN2mK55by0D7Io8diSwH4ODzwiT-_XOEiOtKWKbz1lbNqMdi4vQjtfKJzq3PPFP10e5s40rcTdgYulS3FeBZ31jlmgTc5S2ku9zAAIgAG_lxyaT79vZfkvFoTUfItMRlErYi0lGEpLBBI8c9u_VYw7WNlq8_im0untZgwCPOAJdjQ4k3JJIcHC9JZQzF6W0Sw\",\n" +
                            "    \"access_token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdXRoZW50aWNhdGVkQWNjb3VudCI6eyJpZCI6IjgxNjQ0MyJ9LCJsZWdhY3lUb2tlbiI6eyJ0b2tlbiI6IkZVZGFnaDRNT3JCOWhUM25TZTlydHRqdC0tX2NRcm9zTjJ4MW5IbEIyR091WDFiMEJMNHEwd2FqSzdCaVhndmpPWVlwSVNtTnpZNE5hdmdQc3Z2SVd2TDlZVWllQ01ZWlBrWU1ieW13akdjWXo3T043bWRvUm1jZGFPYjZpS3JtZ25KZnQ5V1M5amt0bDNlV3J6XzJLSnllN01qRnVfMGpiM2Z1Y042dUxTQmh6NHJOSWE2d0tTQUdkOU0zTFJ1V3ZQckE4R2lGR1FOTXk4bmFUSG0zQ3JkVFFuLTAtWVdQclhRQVh4SzlCZTl6MlBHUWd0dEVkZkxpLTU5bER1M1dZRUVjN1gtMEI0VTBUZGxzZGF5dDNlaGVtYUc4UlB5T0ZoVUp1UDZSc0FUR3l0VEtOMDlLT3kxZkdmS2hCQmZGeDQtdnJ1c0NZdDdTaGJGdThGMUoxWEJXeHMwNFh1QjhMakRjWEloRUZxOXdZdDhuWlJVNnk4dVZFV1J4NVdORThMVVppbkVXNFJhcy1WWS1HbndST0ptYVQ0V3JIeUlHSUY3ekZQdmpzRDY0eW1pbGFCR1RucnYxUVBFdV9GLVMyNy1iaUo0ZVNGOVgxTk5tbXAyQ0hqV01XMl9fN3R1dWZWc0NHbW85Zmo3elRucnFxVVZXQVdCaEhrczZUM2cxVDdMaFVnIiwidG9rZW5UeXBlIjoiYmVhcmVyIn0sImlhdCI6MTU3MjI3ODkwNSwiZXhwIjoxNTczNDg4NTA1fQ.0UUO8y1TZrFa7qp0022XRDL5fcoDo8wmPGWQzX1tfPg\",\n" +
                            "    \"expires_in\": 1209599\n" +
                            "}"
                )
        )
    }

    @Test
    fun `should do login`() {
        val service = given {
            networkModule.provideMobileRetrofit(
                mockWebServer.url(" ").toString(),
                networkModule.providerGsonConverter(),
                networkModule.provideHttpClient(networkModule.provideLoggingInterceptor())
            ).create(LoginService::class.java)
        }

        val result = whenever {
            runBlocking {
                service.login(apiLoginParamsMap)
            }
        }

        then {
            result shouldEqual APIToken(
                "bearer",
                "DxnbRUMpVMZyoragxka3zaRVQO-hTUfzTnXKPdB-iUr_Z3_Qo8f7z6Wd4UQgZ11GRmBCZ5YT6DfulXsFZeie1otzV5_u5Tpq-n_jThT_ci9__QI07sPG1ziXlM_XRjGJNdWszJFrzC1Pa-r5g-d3Ha8CP2dHP8mj3cpQBJxXrT9CPExJHQnFeSTOhMGl3Sn0RPNV4I-1uhnXXRsrNATp0dRluJIwln6Z1JShEEe6L15Kx14UpiUgmFi4W32MrREmDOi7jvwXgkDuBGvTtzAHJYesj6ldaPn5CSX59VCT-hajXH3SOEptNKo2taFH1N22XZ86IZN2mK55by0D7Io8diSwH4ODzwiT-_XOEiOtKWKbz1lbNqMdi4vQjtfKJzq3PPFP10e5s40rcTdgYulS3FeBZ31jlmgTc5S2ku9zAAIgAG_lxyaT79vZfkvFoTUfItMRlErYi0lGEpLBBI8c9u_VYw7WNlq8_im0untZgwCPOAJdjQ4k3JJIcHC9JZQzF6W0Sw",
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdXRoZW50aWNhdGVkQWNjb3VudCI6eyJpZCI6IjgxNjQ0MyJ9LCJsZWdhY3lUb2tlbiI6eyJ0b2tlbiI6IkZVZGFnaDRNT3JCOWhUM25TZTlydHRqdC0tX2NRcm9zTjJ4MW5IbEIyR091WDFiMEJMNHEwd2FqSzdCaVhndmpPWVlwSVNtTnpZNE5hdmdQc3Z2SVd2TDlZVWllQ01ZWlBrWU1ieW13akdjWXo3T043bWRvUm1jZGFPYjZpS3JtZ25KZnQ5V1M5amt0bDNlV3J6XzJLSnllN01qRnVfMGpiM2Z1Y042dUxTQmh6NHJOSWE2d0tTQUdkOU0zTFJ1V3ZQckE4R2lGR1FOTXk4bmFUSG0zQ3JkVFFuLTAtWVdQclhRQVh4SzlCZTl6MlBHUWd0dEVkZkxpLTU5bER1M1dZRUVjN1gtMEI0VTBUZGxzZGF5dDNlaGVtYUc4UlB5T0ZoVUp1UDZSc0FUR3l0VEtOMDlLT3kxZkdmS2hCQmZGeDQtdnJ1c0NZdDdTaGJGdThGMUoxWEJXeHMwNFh1QjhMakRjWEloRUZxOXdZdDhuWlJVNnk4dVZFV1J4NVdORThMVVppbkVXNFJhcy1WWS1HbndST0ptYVQ0V3JIeUlHSUY3ekZQdmpzRDY0eW1pbGFCR1RucnYxUVBFdV9GLVMyNy1iaUo0ZVNGOVgxTk5tbXAyQ0hqV01XMl9fN3R1dWZWc0NHbW85Zmo3elRucnFxVVZXQVdCaEhrczZUM2cxVDdMaFVnIiwidG9rZW5UeXBlIjoiYmVhcmVyIn0sImlhdCI6MTU3MjI3ODkwNSwiZXhwIjoxNTczNDg4NTA1fQ.0UUO8y1TZrFa7qp0022XRDL5fcoDo8wmPGWQzX1tfPg",
                1209599
            )
        }
    }
}
