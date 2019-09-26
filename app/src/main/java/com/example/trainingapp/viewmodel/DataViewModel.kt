package com.example.trainingapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trainingapp.models.DataModel
import com.example.trainingapp.repositories.DataRepository
import com.example.trainingapp.repositories.DataRepositoryImpl
import com.example.trainingapp.tools.Mockable
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@Mockable
class DataViewModel @Inject constructor(
    private var repository: DataRepository
): CoroutineScope, ViewModel() {

    private var itemContent = MutableLiveData<DataModel>().apply {
        postValue(DataModel())
    }
    private var lstContent = MutableLiveData<List<DataModel>>().apply {
        postValue(emptyList())
    }

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    init {
        job = SupervisorJob()
    }

    init {
        getDefaultData()//Option by default, cannot list recent content
    }

    private fun getDefaultData() {
        launch {
            val result = repository.getContentByName("Jumanji")
            if (!result.isNullOrEmpty()) {
                getContentList().postValue(result)
            }
        }
    }

    fun getContentByName(name: String) {
        launch {
            val result = repository.getContentByName(name)
            if (!result.isNullOrEmpty()) {
                getContentList().postValue(result)
            }
        }
    }

    fun getContentById(id: String) {
        launch {
            val result = repository.getContentById(id)
            getContentItem().postValue(result)
        }
    }

    fun getContentList() = lstContent

    fun getContentItem() = itemContent

}