package com.example.trainingapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.trainingapp.mock
import com.example.trainingapp.models.DataModel
import com.example.trainingapp.movieTemplateBruce
import com.example.trainingapp.moviesLstDefaultTemplate
import com.example.trainingapp.repositories.DataRepositoryImpl
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.Spy

class DataViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Spy
    private lateinit var viewModel: DataViewModel

    private val itemObserver: Observer<DataModel> = mock()
    private val lstObserver: Observer<List<DataModel>> = mock()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel.getContentList()?.observeForever(lstObserver)
        viewModel.getContentItem()?.observeForever(itemObserver)

    }

    @Test
    fun getContentByNameMocked() {
        `when`(viewModel.getContentByName("")).then {
            DataRepositoryImpl.postManualList(moviesLstDefaultTemplate)
        }

        viewModel.getContentByName("")

        verify(viewModel, times(1)).getContentByName("")

        verify(lstObserver).onChanged(moviesLstDefaultTemplate)

        viewModel.getContentList()?.let {
            assertEquals(it.value, moviesLstDefaultTemplate)
        } ?: println("ViewModel List is null")
    }

    @Test
    fun getContentByIdMocked() {
        `when`(viewModel.getContentById("")).then {
            DataRepositoryImpl.postManualItem(movieTemplateBruce)
        }

        viewModel.getContentById("")

        verify(viewModel, times(1)).getContentById("")

        verify(itemObserver).onChanged(movieTemplateBruce)

        viewModel.getContentItem()?.let {
            assertEquals(it.value, movieTemplateBruce)
        } ?: println("ViewModel Item is null")

    }

    @Test
    fun getContentList() {
        assertNotNull(viewModel.getContentList())
    }

    @Test
    fun getContentItem() {
        assertNotNull(viewModel.getContentItem())
    }
}