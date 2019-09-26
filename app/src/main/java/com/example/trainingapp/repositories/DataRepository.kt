package com.example.trainingapp.repositories

import androidx.lifecycle.MutableLiveData
import com.example.trainingapp.models.DataModel
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataRepository {

    suspend fun getContentByName(name: String): List<DataModel>?
    suspend fun getContentById(id: String): DataModel?
}