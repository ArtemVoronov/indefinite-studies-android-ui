package com.indefinitestudies.app.services

import com.indefinitestudies.app.TaskDTO
import com.indefinitestudies.app.TaskListDTO
import retrofit2.Call
import retrofit2.http.*

interface TasksApiClient {
    @GET("tasks")
    fun list(@Query("limit") limit: Int? = 50, @Query("offset") offset: Int? = 0): Call<TaskListDTO>

    @GET("task/{id}")
    operator fun get(@Path("id") taskId: Int): Call<TaskDTO>

    @POST("task")
    fun create(@Path("id") taskId: Int): Call<TaskDTO>

    @PUT("task/{id}")
    fun update(@Path("id") taskId: Int, @Body taskDTO: TaskDTO?): Call<TaskDTO>

    @DELETE("task")
    fun delete(@Path("id") taskId: Int): Call<TaskDTO>
}