package com.indefinitestudies.app.services

import android.util.Log
import com.indefinitestudies.app.TaskListDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.lang.Exception

class TasksService (url: String) {
    private val client = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(JacksonConverterFactory.create())
        .build()
        .create(TasksApiClient::class.java)

    fun getTaskList(onResponse: (TaskListDTO) -> Unit, limit: Int? = 50, offset: Int? = 0) { // TODO: add uploading of tasks by scrolling or all at once
        try {
            val call = client.list()
            call.enqueue(object : Callback<TaskListDTO> {
                override fun onResponse(call: Call<TaskListDTO>, response: Response<TaskListDTO>) {
                    if (response.code() == 200) {
                        val tasks = response.body()!!
                        onResponse(tasks)
                    }
                }
                override fun onFailure(call: Call<TaskListDTO>, t: Throwable) {
                    Log.e("MAIN_ACTIVITY", "Unable to list tasks")
                }
            })
        } catch (ex : Exception) {
            Log.e("MAIN_ACTIVITY", "Unable to list tasks")
        }
    }
}