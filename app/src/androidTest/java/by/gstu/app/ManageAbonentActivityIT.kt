package by.gstu.app

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.RootMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import by.gstu.app.bean.Platform
import org.hamcrest.Matchers
import org.hamcrest.Matchers.*

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import java.util.regex.Matcher

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ManageAbonentActivityIT {

    @get:Rule
    val manageAbonentActivityRule = ActivityTestRule(ManageAbonentActivity::class.java)

    @Test
    fun testDeleteButtonIsNotDisplayed() {
        // Context of the app under test.
        onView(withId(R.id.delete_button)).check(matches(not(isDisplayed())))
    }

    @Test
    fun testSaveButtonIsDisplayed() {
        // Context of the app under test.
        onView(withId(R.id.save_changes_button)).check(matches(isDisplayed()))
    }

    @Test
    fun testSaveButtonIsClickable() {
        // Context of the app under test.
        onView(withId(R.id.save_changes_button)).check(matches(isClickable()))
    }

    @Test
    fun testDeleteButtonIsClickable() {
        onView(withId(R.id.delete_button)).check(matches(isClickable()))
    }

    @Test
    fun testAbonentNameInputIsEmpty() {
        onView(withId(R.id.abonent_name)).check(matches(withText("")))
    }

    @Test
    fun testAbonentAgeInputIsEmpty() {
        onView(withId(R.id.abonent_age)).check(matches(withText("")))
    }

    @Test
    fun testAbonentNameHasAnyValue() {
        onView(withId(R.id.abonent_name))
                .perform(typeText("Mik"))
                .check(matches(withText("Mik")))
    }

    @Test
    fun testAbonentAgeHasAnyValue() {
        onView(withId(R.id.abonent_age))
                .perform(typeText("18"))
                .check(matches(withText("18")))
    }

    @Test
    fun testAbonentNameEditTextIsFocused() {
        onView(withId(R.id.abonent_name))
                .perform(typeText("name"))
                .check(matches(isFocused()))
    }

    @Test
    fun testAbonentAgeEditTextIsFocused() {
        onView(withId(R.id.abonent_age))
                .perform(typeText("12"))
                .check(matches(isFocused()))
    }
}