package com.indefinitestudies.app

data class TaskListDTO(
    val limit: Int = 0,
    val offset: Long = 50,
    val count: Int = 0,
    val data: List<TaskDTO> = listOf<TaskDTO>()
)