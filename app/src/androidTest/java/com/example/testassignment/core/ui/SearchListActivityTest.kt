package com.example.testassignment.core.ui

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
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
class SearchListActivityTest{

    @get: Rule
    val activityRule = ActivityScenarioRule(SearchListActivity::class.java)

    @Test
    fun test_IsActivityInView(){
        onView(withId(R.id.relativeLayoutCover)).check(matches(isDisplayed()))
    }

    @Test
    fun test_IsHeaderBarInView(){
        onView(withId(R.id.headerCover)).check(matches(isDisplayed()))
    }

    @Test
    fun test_IsRecyclerViewInView(){
        onView(withId(R.id.searchRev)).check(matches(isDisplayed()))
    }

    @Test
    fun test_IsProgressBarInView(){
        onView(withId(R.id.progressCircular)).check(matches(isDisplayed()))
    }

}