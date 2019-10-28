package com.alejandrorios.login

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

/**
 * Created by Alejandro Rios on 2019-10-28
 */
interface MockServerTest {

    var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
