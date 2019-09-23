package com.example.trainingapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.trainingapp.models.DataModel
import com.example.trainingapp.repositories.DataRepository

class DataViewModel: ViewModel() {

    private var itemContent: LiveData<DataModel>? = null
    private var lstContent: LiveData<List<DataModel>>? = null

    init {
        itemContent = DataRepository.getItem()
        lstContent = DataRepository.getListData()
        getContentByName("Avengers")//Option by default, cannot list recent content
    }

    fun getContentByName(name: String) {
        DataRepository.getContentByName(name)
    }

    fun getContentById(id: String) {
        DataRepository.getContentById(id)
    }

    fun getContentList() = lstContent

    fun getContentItem() = itemContent

    fun setContentList(content: List<DataModel>) {
        lstContent?.let {

        }
    }
}