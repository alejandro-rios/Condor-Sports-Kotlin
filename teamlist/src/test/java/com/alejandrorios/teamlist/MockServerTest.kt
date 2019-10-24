package com.alejandrorios.teamlist

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

/**
 * Created by Alejandro Rios on 2019-10-24
 */
interface MockServerTest {

    var mockserver: MockWebServer

    @Before
    fun setUp(){
        mockserver = MockWebServer()
        mockserver.start()
    }

    @After
    fun tearDown() {
        mockserver.shutdown()
    }
}
