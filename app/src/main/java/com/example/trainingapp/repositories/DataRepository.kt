package com.example.trainingapp.repositories

import com.example.trainingapp.models.DataModel
import dagger.Module

interface DataRepository {

    suspend fun getContentByName(name: String): List<DataModel>?
    suspend fun getContentById(id: String): DataModel?
}