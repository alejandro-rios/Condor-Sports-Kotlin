package com.alejandrorios.condorsports.ui

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import androidx.test.uiautomator.UiDevice
import com.alejandrorios.condorsports.CondorSportsActivity
import com.alejandrorios.condorsports.R
import com.alejandrorios.condorsports.utils.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Alejandro Rios on 2019-10-29
 */
@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @Rule
    @JvmField
    var customActivityTestRule = ActivityTestRule(CondorSportsActivity::class.java)

    private var device: UiDevice? = null

    @Before
    fun setup() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }

    @Test
    fun shouldDoLogin() {
        wait(TWO_SECS)
        isTextDisplayed("Login")
        setText(R.id.edUserName, "RN99999999")
        setText(R.id.edPassword, "password")
        buttonClick("Login")
        wait(TWO_SECS)
        isTextDisplayed("Condor Sports")
    }

    @Test
    fun shouldNotDoLogin() {
        wait(TWO_SECS)
        isTextDisplayed("Login")
        setText(R.id.edUserName, "RN99999")
        setText(R.id.edPassword, "password")
        buttonClick("Login")
        wait(TWO_SECS)
        isViewNotDisplayed(R.id.rvTeams)
    }
}
