package com.example.trainingapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.trainingapp.models.DataModel
import com.example.trainingapp.view.MainActivity
import com.example.trainingapp.viewmodel.DataViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var activity: MainActivity

    private lateinit var viewModel: DataViewModel
/*
    Required to test navigation components
    private val mockNavController = mock(NavController::class.java)*/

    private val listObserver: Observer<List<DataModel>> = mock()
    private val itemObserver: Observer<DataModel> = mock()

    @Captor
    private lateinit var lstCaptor: ArgumentCaptor<List<DataModel>>

    @Captor
    private lateinit var itemCaptor: ArgumentCaptor<DataModel>

    @Before
    fun setupEnviroment() {
        MockitoAnnotations.initMocks(this)
        activity = mock(MainActivity::class.java)
        viewModel = DataViewModel()
        viewModel.getContentList()?.apply {
            observeForever(listObserver)
        } ?: println("Content List is null")
        viewModel.getContentItem()?.apply {
            observeForever(itemObserver)
        } ?: println("Content Item is null")
    }

    @Test
    fun checkSearchIsNeverCalled() {
        verify(activity, never()).searchQuery("")
    }

    @Test
    fun searchWithEmptyQuery() {

        activity.searchQuery("")

        verify(activity).searchQuery("")

        verify(listObserver, times(1)).onChanged(lstCaptor.capture())
    }

    @Test
    fun searchWithNullQuery() {
        activity.searchQuery(null)

        verify(activity).searchQuery(null)

        verify(listObserver, times(1)).onChanged(lstCaptor.capture())
    }

    @Test
    fun searchWithContentQuery() {
        activity.searchQuery("Bruce")

        verify(activity).searchQuery("Bruce")

        viewModel.getContentByName("Bruce")

        verify(listObserver, times(1)).onChanged(lstCaptor.capture())
        println("ListObserver: ${lstCaptor.value}")
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

    @Test
    fun testVerifyItemUpdate() {
        verify(itemObserver, times(1)).onChanged(itemCaptor.capture())
        println("ItemObserver: ${itemCaptor.value}")
    }

}
