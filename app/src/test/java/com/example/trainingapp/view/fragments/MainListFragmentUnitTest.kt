package com.example.trainingapp.view.fragments

import com.example.trainingapp.emptyListTemplate
import com.example.trainingapp.moviesLstDefaultTemplate
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainListFragmentUnitTest {

    @Mock
    private lateinit var fragment: MainListFragment

    @Before
    fun setupEnviroment() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testInitRecycler() {
        verify(fragment, never()).initRecycler()
    }

    @Test
    fun testAdapterEmptyInit() {
        fragment.updateAdapterItems(emptyListTemplate)
        verify(fragment, times(1)).updateAdapterItems(emptyListTemplate)
    }

    @Test
    fun testAdapterWithDataInit() {
        fragment.updateAdapterItems(moviesLstDefaultTemplate)
        verify(fragment, times(1)).updateAdapterItems(moviesLstDefaultTemplate)
    }

    @Test
    fun testAdapterItemClick() {
        fragment.handleItemClick("tt0315327")
        verify(fragment, times(1)).handleItemClick("tt0315327")
    }

}