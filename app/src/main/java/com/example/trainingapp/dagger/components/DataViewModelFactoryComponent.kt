package com.example.trainingapp.dagger.components

import com.example.trainingapp.view.MainActivity
import com.example.trainingapp.viewmodel.DataViewModel
import com.example.trainingapp.viewmodel.DataViewModelFactory
import dagger.Component

@Component
interface DataViewModelFactoryComponent {

    fun get(): DataViewModelFactory<DataViewModel>

    fun inject(activity: MainActivity)
}