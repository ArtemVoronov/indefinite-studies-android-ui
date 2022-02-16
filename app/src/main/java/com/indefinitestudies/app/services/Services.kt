package com.indefinitestudies.app.services

object Services {
    val TASKS_SERVICE: TasksService = TasksService("http://192.168.0.18:8080/") // TODO: use config file to separate local/dev/prod environments
}