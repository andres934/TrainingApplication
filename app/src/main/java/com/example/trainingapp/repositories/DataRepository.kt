package com.example.trainingapp.repositories

import androidx.lifecycle.MutableLiveData
import com.example.trainingapp.config.RetrofitHelper
import com.example.trainingapp.models.DataModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

object DataRepository : CoroutineScope {

    private var item = MutableLiveData<DataModel>().apply { postValue(
        DataModel()
    ) }
    private var listData = MutableLiveData<List<DataModel>>().apply { postValue(emptyList()) }
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    init {
        job = SupervisorJob()
    }

    fun getListData() = listData
    fun getItem() = item

    fun getContentByName(name: String) {
        launch {
            val result = RetrofitHelper().getContentByNameService(name)
            if (result.isNotEmpty()) {
                listData.postValue(result)
            }
        }
    }

    fun getContentById(id: String) {
        launch {
            val result = RetrofitHelper().getContentByIdService(id)
            item.postValue(result)
        }
    }
}