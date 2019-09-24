package com.example.trainingapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.trainingapp.models.DataModel
import com.example.trainingapp.viewmodel.DataViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailsFragmentUnitTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: DataViewModel

    private val itemObserver: Observer<DataModel> = mock()

    @Captor
    private lateinit var itemCaptor: ArgumentCaptor<DataModel>

    @Before
    fun setupEnviroment() {
        MockitoAnnotations.initMocks(this)
        viewModel = DataViewModel()
        viewModel.getContentItem()?.apply {
            observeForever(itemObserver)
        } ?: println("Content item is null")
    }

    @Test
    fun testVerifyItemUpdate() {
        Mockito.verify(itemObserver, Mockito.times(1)).onChanged(itemCaptor.capture())
        println("ItemObserver: ${itemCaptor.value}")
    }
}