package com.example.trainingapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.trainingapp.repositories.DataRepository
import com.example.trainingapp.view.MainActivity
import com.example.trainingapp.viewmodel.DataViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainActivityUnitTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var activity: MainActivity

    private lateinit var repository: DataRepository

    private lateinit var viewModel: DataViewModel
/*
    Required to test navigation components
    private val mockNavController = mock(NavController::class.java)*/

    @Before
    fun setupEnviroment() {
        MockitoAnnotations.initMocks(this)
        activity = mock()
        repository = mock()
        viewModel = spy(DataViewModel::class.java)
    }

    @Test
    fun checkSearchIsNeverCalled() {
        verify(activity, never()).searchQuery("")
    }

    @Test
    fun searchWithEmptyQuery() {
        activity.searchQuery("")

        verify(activity, times(1)).searchQuery("")
    }

    @Test
    fun searchWithNullQuery() {
        activity.searchQuery(null)

        verify(activity, times(1)).searchQuery(null)
    }

    @Test
    fun searchWithContentQuery() {
        activity.searchQuery("Bruce")

        verify(activity, times(1)).searchQuery("Bruce")

        //verify(repository).getContentByName("Bruce")
    }

    /*@Test
    fun testNavigationMainScreen() {

        val mainListScenario = launchFragmentInContainer<MainListFragment>()

        mainListScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }

        val directions =
            MainListFragmentDirections.actionMainListFragmentToDetailsFragment(
                movieTemplateBruce.imdbID
            )

        verify(mockNavController).navigate(directions)
    }*/

}
