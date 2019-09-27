package com.example.trainingapp.repositories

import com.example.trainingapp.config.RetrofitHelper
import com.example.trainingapp.models.DataModel
import com.example.trainingapp.tools.Mockable
import javax.inject.Inject

@Mockable
class DataRepositoryImpl @Inject constructor(): DataRepository {

    override suspend fun getContentByName(name: String): List<DataModel> = RetrofitHelper().getContentByNameService(name)

    override suspend fun getContentById(id: String): DataModel? = RetrofitHelper().getContentByIdService(id)

}