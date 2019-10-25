package com.alejandrorios.teamlist

import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import androidx.test.uiautomator.UiDevice
import com.alejandrorios.teamlist.utils.TWO_SECS
import com.alejandrorios.teamlist.utils.isTextDisplayed
import com.alejandrorios.teamlist.utils.wait
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Alejandro Rios on 2019-10-26
 */
@RunWith(AndroidJUnit4::class)
class TeamListFragmentTest {

    @Rule
    @JvmField
    var customActivityTestRule = ActivityTestRule(TeamListActivity::class.java)

    private var device: UiDevice? = null

    @Before
    fun setup() {
        device = UiDevice.getInstance(getInstrumentation())
    }

    @Test
    fun teamsFragmentTest() {
        wait(TWO_SECS)
        isTextDisplayed("Condor Sports")
    }
}
