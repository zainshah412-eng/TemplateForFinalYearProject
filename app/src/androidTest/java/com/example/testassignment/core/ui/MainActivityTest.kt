package com.example.testassignment.core.ui

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.android.R
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    private lateinit var activity: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        activity = ActivityScenario.launch(MainActivity::class.java)
        activity.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun test_IsActivityInView() {
        onView(withId(R.id.container)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_IsTextViewInView() {
        onView(withId(R.id.searchTitleText)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_IsEditTextInView() {
        onView(withId(R.id.searchEditText)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_IsSubmitInView() {
        onView(withId(R.id.btnSearch)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_IsTextIsDisplayed() {
        onView(withId(R.id.searchTitleText)).check(ViewAssertions.matches(withText(R.string.search)))
    }

    @Test
    fun testClickOnSubmitButton() {
        val searchWord = "foo"
        onView(withId(R.id.edtSearch)).perform(ViewActions.typeText(searchWord))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.btnSearch)).perform(click())
    }

    @Test
    fun testClickOnSubmitButtonShowsSearchActivity() {
        val searchWord = "foo"
        onView(withId(R.id.edtSearch)).perform(ViewActions.typeText(searchWord))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.btnSearch)).perform(click())
        onView(withId(R.id.relativeLayoutCover)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


    @Test
    fun testBackPressButtonToMainActivity() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        val searchWord = "foo"
        onView(withId(R.id.edtSearch)).perform(ViewActions.typeText(searchWord))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.btnSearch)).perform(click())
        pressBack()
    }

    @Test
    fun testBackPressArrowToMainActivity() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        val searchWord = "foo"
        onView(withId(R.id.edtSearch)).perform(ViewActions.typeText(searchWord))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.btnSearch)).perform(click())
        onView(withId(R.id.backButton)).perform(click())
    }


}