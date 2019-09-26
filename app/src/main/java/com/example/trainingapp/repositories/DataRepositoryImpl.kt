package com.example.trainingapp.repositories

import androidx.lifecycle.MutableLiveData
import com.example.trainingapp.config.RetrofitHelper
import com.example.trainingapp.models.DataModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DataRepositoryImpl : DataRepository {

    override suspend fun getContentByName(name: String): List<DataModel>? = RetrofitHelper().getContentByNameService(name)

    override suspend fun getContentById(id: String): DataModel? = RetrofitHelper().getContentByIdService(id)

}