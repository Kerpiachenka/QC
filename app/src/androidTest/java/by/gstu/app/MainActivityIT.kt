package by.gstu.app

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.RootMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import by.gstu.app.adapter.AbonentRecyclerViewAdapter.AbonentViewHolder
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
class MainActivityIT {

    @get:Rule
    val mainActivityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testThatManageAbonentButtonIsDisplayed() {
        onView(withId(R.id.create_abonent_page_button)).check(matches(isDisplayed()))
    }

    @Test
    fun testThatManageAbonentButtonIsClickable() {
        onView(withId(R.id.create_abonent_page_button)).check(matches(isClickable()))
    }

    @Test
    fun testThatPlatformActiveGroupButtonIsDisplayed() {
        onView(withId(R.id.platform_active_group_page_button)).check(matches(isDisplayed()))
    }

    @Test
    fun testThatPlatformActiveGroupButtonIsClickable() {
        onView(withId(R.id.platform_active_group_page_button)).check(matches(isClickable()))
    }

    @Test
    fun testThatRecyclerViewIsDisplayed() {
        onView(withId(R.id.abonent_list)).check(matches(isDisplayed()))
    }
}