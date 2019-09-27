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
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainActivityEspressoTest {

    @Rule
    @JvmField
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java)

    private lateinit var viewModel: DataViewModel

    @Mock
    private lateinit var repository: DataRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        setUpMockedData()

        viewModel = DataViewModel(repository)
        activityRule.activity.vmFactory = DataViewModelFactory(viewModel)
    }

    fun setUpMockedData() {
        runBlocking {
            `when`(repository.getContentByName("Bruce")).thenReturn(moviesLstDefaultTemplate)
        }

        runBlocking {
            `when`(repository.getContentById("tt4154796")).thenReturn(movieTemplateAvengers)
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

        onView(withId(R.id.recycler))
            .check(matches(Tools.recyclerViewSizeMatcher(moviesLstDefaultTemplate.size)))

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