package com.example.trainingapp.repositories

import androidx.lifecycle.MutableLiveData
import com.example.trainingapp.models.DataModel

interface DataRepository {

    fun getListData(): MutableLiveData<List<DataModel>>
    fun getItem(): MutableLiveData<DataModel>
    fun getContentByName(name: String)
    fun getContentById(id: String)
    fun postManualList(data: List<DataModel>)
    fun postManualItem(data: DataModel)
}