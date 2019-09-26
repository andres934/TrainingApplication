package com.example.trainingapp.view.fragments

import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.trainingapp.mock
import com.example.trainingapp.models.DataModel
import com.example.trainingapp.movieLstTemplateBruce
import com.example.trainingapp.viewmodel.DataViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.Spy

class DetailsFragmentUnitTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var fragment: DetailsFragment

    @Spy
    private lateinit var viewModel: DataViewModel

    private val itemObserver: Observer<DataModel> = mock()

    @Before
    fun setupEnviroment() {
        MockitoAnnotations.initMocks(this)

        viewModel.getContentItem()?.apply {
            observeForever(itemObserver)
        } ?: println("Content item is null")
    }

    @Test
    fun testItemInitiationEmptyBundle() {
        fragment.updateCurrentItemById(null)
        verify(fragment, times(1)).updateCurrentItemById(null)
    }

    @Test
    fun testItemInitiationWithBundle() {
        val bundle: Bundle = mock()
        bundle.putString("idContent", "tt0315327")

        fragment.updateCurrentItemById(bundle)

        verify(fragment, times(1)).updateCurrentItemById(bundle)
    }

    @Test
    fun testVerifyItemUpdate() {
        val bundle: Bundle = mock()
        bundle.putString("idContent", "tt0315327")

        fragment.updateCurrentItemById(bundle)
        verify(fragment, times(1)).updateCurrentItemById(bundle)

        `when`(viewModel.getContentById("tt0315327")).then {
            viewModel.postManualItem(movieLstTemplateBruce)
        }

        viewModel.getContentById("tt0315327")
        verify(viewModel, times(1)).getContentById("tt0315327")

        verify(itemObserver, times(1)).onChanged(movieLstTemplateBruce)

        viewModel.getContentItem()?.let {
            assertEquals(movieLstTemplateBruce, it.value)
        }
    }
}