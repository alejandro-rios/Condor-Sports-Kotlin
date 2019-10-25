package com.alejandrorios.teamlist.utils

import android.app.Activity
import android.preference.PreferenceManager
import android.view.View
import androidx.test.espresso.AmbiguousViewMatcherException
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import junit.framework.Assert.assertTrue
import org.hamcrest.Description
import org.hamcrest.Matcher
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

//fun loginTestUser(
//    user: String,
//    password: String
//) {
//    buttonClick(R.id.welcome_button_login)
//    closeSoftKeyboard()
//    onView(withId(R.id.login_edittext_username))
//        .perform(
//            replaceText(user)
//        )
//    closeSoftKeyboard()
//    onView(withId(R.id.login_edittext_password))
//        .perform(
//            replaceText(password)
//        )
//    closeSoftKeyboard()
//    onView(withId(R.id.login_button_login)).perform(click())
//    wait(THREE_SECS)
//}

fun buttonClick(text: String) {
    onView(withText(text)).check(matches(isDisplayed()))
        .perform(click())
}

fun buttonClick(id: Int) {
    onView(withId(id)).check(matches(isDisplayed()))
        .perform(click())
}

fun scrollToView(id: Int) {
    onView(withId(id))
        .perform(ViewActions.scrollTo())
}

fun recyclerViewItemClick(
    id: Int,
    pos: Int
) {
    onView(withId(id))
        .check(matches(isDisplayed()))
        .perform(
            RecyclerViewActions.actionOnItemAtPosition<androidx.recyclerview.widget.RecyclerView.ViewHolder>(
                pos,
                click()
            )
        )
}

fun isTextDisplayed(text: String) {
    var isDisplayed = true
    onView(ViewMatchers.withSubstring(text))
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
