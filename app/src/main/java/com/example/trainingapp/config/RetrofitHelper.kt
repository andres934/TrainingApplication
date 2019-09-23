package com.example.trainingapp.config

import com.example.trainingapp.models.DataModel
import com.example.trainingapp.models.SearchModel
import com.example.trainingapp.interfaces.ApiRest
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.resume

class RetrofitHelper {

    private fun getRetrofitBuilder(baseUrl: String? = null): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl ?: DefaultData.defaultApiUrl)
            .build()
    }

    suspend fun getContentByNameService(name: String) = suspendCancellableCoroutine<List<DataModel>> { c ->

        val retrofit = getRetrofitBuilder()

        val myService = retrofit.create(ApiRest::class.java)
        val urlDestination = "?s=$name&apikey=${DefaultData.omdbApiKey}"
        myService.getContentByName(urlDestination).enqueue(object: Callback<SearchModel> {

            override fun onResponse(
                call: Call<SearchModel>,
                response: Response<SearchModel>
            ) {
                when (response.isSuccessful) {
                    true -> {
                        val result: List<DataModel> = response.body()?.search ?: emptyList()

                        c.resume(result)
                    }
                    else -> {
                        c.resume(emptyList())
                    }
                }

            }

            override fun onFailure(call: Call<SearchModel>, t: Throwable) {
                c.resume(emptyList())
            }

        })
    }

    suspend fun getContentByIdService(id: String) = suspendCancellableCoroutine<DataModel?> { c ->

        val retrofit = getRetrofitBuilder()

        val myService = retrofit.create(ApiRest::class.java)
        val urlDestination = "?i=$id&apikey=${DefaultData.omdbApiKey}"
        myService.getContentById(urlDestination).enqueue(object: Callback<DataModel> {

            override fun onResponse(
                call: Call<DataModel>,
                response: Response<DataModel>
            ) {
                c.resume(response.body())
            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                c.resume(null)
            }

        })
    }
}