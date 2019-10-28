package com.alejandrorios.condorsports.ui

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector
import com.alejandrorios.condorsports.R
import com.alejandrorios.condorsports.utils.*
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
    var customActivityTestRule = ActivityTestRule(CondorSportsActivity::class.java)

    private var device: UiDevice? = null

    @Before
    fun setup() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }

    @Test
    fun shouldSeeTeamList() {
        wait(TWO_SECS)
        isTextDisplayed("Condor Sports")
        isViewDisplayed(R.id.rvTeams)
    }

    @Test
    fun onScrollShouldFabDisappear() {
        wait(TWO_SECS)
        isTextDisplayed("Condor Sports")
        isViewDisplayed(R.id.rvTeams)
        performScrollUp()
        wait(THREE_SECS)
        isViewNotDisplayed(R.id.fabChangeLeague)
    }

    @Test
    fun shouldSeeAndChangeFromTeam() {
        wait(TWO_SECS)
        isTextDisplayed("Condor Sports")
        isViewDisplayed(R.id.rvTeams)
        fabClick()
        val account: UiObject = device!!.findObject(UiSelector().text("Russian Premier League"))
        account.clickAndWaitForNewWindow()
        wait(TWO_SECS)
        isViewDisplayed(R.id.rvTeams)
        isTextDisplayed("Amkar")
    }

    @Test
    fun shouldClickOnTeam() {
        wait(TWO_SECS)
        isTextDisplayed("Condor Sports")
        isViewDisplayed(R.id.rvTeams)
        recyclerViewItemClick(device!!, 0)
    }
}
