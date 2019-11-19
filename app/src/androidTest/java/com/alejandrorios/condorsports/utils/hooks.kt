package com.alejandrorios.condorsports.utils

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.AmbiguousViewMatcherException
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector
import com.alejandrorios.condorsports.R
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher

/**
 * Created by Alejandro Rios on 2019-10-26
 */
fun getCurrentActivity(): Activity? {
    var currentActivity: Activity? = null

    getInstrumentation().runOnMainSync {
        val resumedActivity = ActivityLifecycleMonitorRegistry.getInstance()
            .getActivitiesInStage(Stage.RESUMED)
        val it = resumedActivity.iterator()
        if (it.hasNext()) {
            currentActivity = it.next()
        }
    }

    return currentActivity
}

fun goToTeamDetails(device: UiDevice, pos: Int = 0) {
    wait(TWO_SECS)
    isViewDisplayed(R.id.rvTeams)
    recyclerViewItemClick(device, pos)
    wait(TWO_SECS)
}

fun setText(id: Int, text: String) {
    onView(withId(id))
        .perform(
            ViewActions.replaceText(text)
        )
}

fun buttonClick(text: String) {
    onView(withText(text)).check(matches(isDisplayed()))
        .perform(click())
}

fun buttonClick(id: Int) {
    var isDisplayed = true
    onView(allOf(withId(id)))
        .withFailureHandler { error, _ ->
            isDisplayed = error is AmbiguousViewMatcherException
        }
        .check(matches(isDisplayed()))
        .perform(click())
}

fun scrollToView(id: Int) {
    onView(withId(id))
        .perform(ViewActions.scrollTo())
}

fun recyclerViewItemClick(
    device: UiDevice,
    pos: Int
) {
    val recyclerView: UiObject =
        device.findObject(UiSelector().className("androidx.recyclerview.widget.RecyclerView"))
    assertTrue(recyclerView.exists())

    recyclerView.getChild(UiSelector().index(pos)).click()
}

fun getBottomBar(
    device: UiDevice
): UiObject {
    val bottomBar: UiObject =
        device.findObject(UiSelector().className("com.google.android.material.bottomnavigation.BottomNavigationView"))
    assertTrue(bottomBar.exists())

    return bottomBar
}

fun isTextDisplayed(text: String) {
    var isDisplayed = true
    onView(withSubstring(text))
        .withFailureHandler { error, _ ->
            isDisplayed = error is AmbiguousViewMatcherException
        }
        .check(matches(isDisplayed()))
    assertTrue(isDisplayed)
}

fun isObjectEnable(id: Int) {
    var isEnable = true
    onView(withId(id))
        .withFailureHandler { error, _ ->
            isEnable = error is AmbiguousViewMatcherException
        }
        .check(matches(isDisplayed()))
    assertTrue(isEnable)
}

fun isViewDisplayed(id: Int) {
    var isDisplayed = true
    onView(withId(id))
        .withFailureHandler { error, _ ->
            isDisplayed = error is AmbiguousViewMatcherException
        }
        .check(matches(isDisplayed()))
    assertTrue(isDisplayed)
}

fun isViewNotDisplayed(id: Int) {
    var isDisplayed = true
    onView(withId(id))
        .withFailureHandler { error, _ ->
            isDisplayed = error is NoSuchFieldError
        }
        .check(matches(isDisplayed()))
    assertFalse(isDisplayed)
}

fun performScrollUp(
    id: Int = android.R.id.content,
    times: Int = 1
) {
    for (i in 0..times) {
        var isDisplayed = true
        onView(withId(id))
            .withFailureHandler { error, _ ->
                isDisplayed = error is AmbiguousViewMatcherException
            }
            .check(matches(isDisplayed()))
            .perform(ViewActions.swipeUp())
    }
}

fun performScrollDown(
    id: Int = android.R.id.content,
    times: Int = 1
) {
    for (i in 0..times) {
        onView(withId(id))
            .perform(ViewActions.swipeDown())
    }
}

fun wait(seconds: Int) {
    Thread.sleep(seconds.toLong() * 1000)
}

fun withIndex(
    matcher: Matcher<View>,
    index: Int
): Matcher<View> {

    return object : TypeSafeMatcher<View>() {
        var currentIndex = 0

        override fun describeTo(description: Description) {
            description.appendText("with index: ")
            description.appendValue(index)
            matcher.describeTo(description)
        }

        public override fun matchesSafely(view: View): Boolean {
            return matcher.matches(view) && currentIndex++ == index
        }
    }
}

fun fabClick() {
    onView(
        allOf(
            withId(R.id.sd_main_fab),
            childAtPosition(
                allOf(
                    withId(R.id.fabChangeLeague),
                    childAtPosition(
                        withClassName(Matchers.`is`("androidx.coordinatorlayout.widget.CoordinatorLayout")),
                        2
                    )
                ),
                3
            ),
            isDisplayed()
        )
    ).perform(click())
}

private fun childAtPosition(
    parentMatcher: Matcher<View>, position: Int
): Matcher<View> {

    return object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("Child at position $position in parent ")
            parentMatcher.describeTo(description)
        }

        public override fun matchesSafely(view: View): Boolean {
            val parent = view.parent
            return parent is ViewGroup && parentMatcher.matches(parent)
                    && view == parent.getChildAt(position)
        }
    }
}
