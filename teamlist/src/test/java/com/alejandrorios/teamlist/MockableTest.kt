package com.alejandrorios.teamlist

import io.mockk.MockKAnnotations
import org.junit.Before

/**
 * Created by Alejandro Rios on 2019-10-24
 */
interface MockableTest {

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }
}
