package com.alejandrorios.condorsports.ui

import androidx.test.espresso.Espresso
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import androidx.test.uiautomator.UiDevice
import com.alejandrorios.condorsports.R
import com.alejandrorios.condorsports.utils.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Alejandro Rios on 2019-10-28
 */
@RunWith(AndroidJUnit4::class)
class TeamDetailsActivityTest {

    @Rule
    @JvmField
    var customActivityTestRule = ActivityTestRule(CondorSportsActivity::class.java)

    private var device: UiDevice? = null

    @Before
    fun setup() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }

    @Test
    fun getToTeamDetails(){
        goToTeamDetails(device!!, 3)
        isTextDisplayed("Oficial Jersey")
    }

    @Test
    fun shouldGoToTeamDetailsAndGoBack() {
        goToTeamDetails(device!!, 3)
        isTextDisplayed("Oficial Jersey")
        Espresso.pressBack()
        wait(TWO_SECS)
        isViewDisplayed(R.id.rvTeams)
    }

//    @Test
//    fun shouldGoToFacebook() {
//        Intents.init()
//        val expectedIntent: Matcher<Intent> =
//            allOf(hasAction(Intent.ACTION_VIEW), hasData("https://mobile.twitter.com/alaves"))
//        intending(expectedIntent).respondWith(Instrumentation.ActivityResult(0, null))
//        goToTeamDetails(device!!)
//        wait(TWO_SECS)
//        onView(withId(R.id.navTwitter)).perform(ViewActions.click())
//        wait(THREE_SECS)
//        intended(expectedIntent)
//        Intents.release()
//    }
}
