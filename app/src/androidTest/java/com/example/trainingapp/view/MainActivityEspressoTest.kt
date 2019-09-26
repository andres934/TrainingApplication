package com.example.trainingapp.view

import android.widget.AutoCompleteTextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.trainingapp.R
import com.example.trainingapp.repositories.DataRepositoryImpl
import com.example.trainingapp.view.adapters.DataAdapter
import com.example.trainingapp.viewmodel.DataViewModel
import com.example.trainingapp.viewmodel.DataViewModelFactory
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainActivityEspressoTest {

    @Rule
    @JvmField
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    private var viewModel: DataViewModel = mock()

    private val factory = DataViewModelFactory(viewModel)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        activityRule.activity.vmFactory = factory

        `when`(viewModel.getContentByName("Bruce")).then {
            println("WHEN CONTENT NAME BRUCE IS SEARCH IS ACTIVATING")
            DataRepositoryImpl.postManualList(moviesLstDefaultTemplate)
            //viewModel.postManualList(moviesLstDefaultTemplate)
        }

        `when`(viewModel.getContentById("tt4154796")).then {
            viewModel.postManualItem(movieTemplateAvengers)
        }
    }

    @Test
    fun a_testMainListFragmentIsLoaded() {
        onView(withId(R.id.mainListFragment)).check(matches(isDisplayed()))
    }

    @Test
    fun b_testSearchViewOpen() {
        onView(withId(R.id.searchBar))
            .perform(click())
    }

    @Test
    fun c_testSearchViewTyping() {
        b_testSearchViewOpen()

        onView(isAssignableFrom(AutoCompleteTextView::class.java))
            .perform(
                typeText("Bruce")
            )
    }

    @Test
    fun d_testSearchSubmit() {

        c_testSearchViewTyping()

        submitSearchAndCloseKeyboard()

        /*onView(withId(R.id.recycler))
            .check(matches(Tools.recyclerViewSizeMatcher(moviesLstDefaultTemplate.size)))*/

    }

    @Test
    fun e_testRecyclerViewGestures() {

        d_testSearchSubmit()

        onView(withId(R.id.recycler))
            .perform(
                swipeUp(),
                swipeDown()
            )
    }

    @Test
    fun f_testRecyclerViewItemClick() {

        e_testRecyclerViewGestures()

        //When RecyclerView Item is Clicked
        onView(withId(R.id.recycler))
            .perform(actionOnItemAtPosition<DataAdapter.ViewHolder>(0, click()))

        //Then DetailsFragment is Opened with ObjectDetails
        onView(withId(R.id.detailsFragment)).check(matches(isDisplayed()))

        println("Current Fragments: ${activityRule.activity.supportFragmentManager.fragments}")

        //onView(withId(R.id.tvTitleContent)).check(matches(withText(movieTemplateBruce.title)))

    }

    private fun submitSearchAndCloseKeyboard() {
        onView(isAssignableFrom(AutoCompleteTextView::class.java))
            .perform(
                pressImeActionButton(),
                closeSoftKeyboard()
            )
    }

}