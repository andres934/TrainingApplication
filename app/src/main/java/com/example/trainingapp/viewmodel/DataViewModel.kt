package com.example.trainingapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.trainingapp.models.DataModel
import com.example.trainingapp.repositories.DataRepository
import com.example.trainingapp.repositories.DataRepositoryImpl

class DataViewModel: ViewModel() {

    private var itemContent: LiveData<DataModel>? = null
    private var lstContent: LiveData<List<DataModel>>? = null
    private var repository: DataRepository = DataRepositoryImpl

    init {
        itemContent = repository.getItem()
        lstContent = repository.getListData()
        getContentByName("Avengers")//Option by default, cannot list recent content
    }

    fun getContentByName(name: String) {
        repository.getContentByName(name)
    }

    fun getContentById(id: String) {
        repository.getContentById(id)
    }

    fun postManualItem(data: DataModel = DataModel()) {
        repository.postManualItem(data)
    }

    fun postManualList(data: List<DataModel> = emptyList()) {
        repository.postManualList(data)
    }

    fun getContentList() = lstContent

    fun getContentItem() = itemContent

}