package com.example.trainingapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.trainingapp.mock
import com.example.trainingapp.models.DataModel
import com.example.trainingapp.movieTemplateBruce
import com.example.trainingapp.moviesLstDefaultTemplate
import com.example.trainingapp.repositories.DataRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class DataViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: DataViewModel

    @Mock
    private lateinit var repository: DataRepositoryImpl

    private val itemObserver: Observer<DataModel> = mock()
    private val lstObserver: Observer<List<DataModel>> = mock()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        setupMockedData()

        viewModel = DataViewModel(repository)

        viewModel.getContentList().observeForever(lstObserver)
        viewModel.getContentItem().observeForever(itemObserver)

    }

    private fun setupMockedData() = runBlocking {
        `when`(repository.getContentByName("")).thenReturn(moviesLstDefaultTemplate)
        `when`(repository.getContentById("")).thenReturn(movieTemplateBruce)
    }

    @Test
    fun getContentByNameMocked() = runBlocking {

            viewModel.getContentByName("")

            verify(repository).getContentByName("")

            verify(lstObserver).onChanged(moviesLstDefaultTemplate)

            return@runBlocking
        }

    @Test
    fun getContentByIdMocked() = runBlocking {

            viewModel.getContentById("")

            verify(repository).getContentById("")

            verify(itemObserver).onChanged(movieTemplateBruce)

            return@runBlocking
        }

}