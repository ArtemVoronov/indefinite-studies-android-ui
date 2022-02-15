package com.indefinitestudies.app

data class TaskListDTO(val limit: Int, val offset: Long, val count: Int, val data: List<TaskDTO>) {
}