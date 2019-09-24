package com.example.trainingapp

import com.example.trainingapp.interfaces.AdapterItemClickListener
import com.example.trainingapp.view.fragments.MainListFragment
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

    private lateinit var listener: AdapterItemClickListener

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
        spy(fragment).apply {
            listener = this

            listener.onItemClickListener("tt0315327")

            verify(this, times(1)).handleItemClick("tt0315327")
        }

    }

}