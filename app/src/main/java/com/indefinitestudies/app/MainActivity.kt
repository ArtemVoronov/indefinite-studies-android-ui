package com.indefinitestudies.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun renderTasks(view: View) {
        try {
            runBlocking { // this: CoroutineScope // TODO: check the appropriate context for coroutine
                launch(Dispatchers.IO) { uploadAndRenderTasks() }
            }
        } finally {
            Log.e("MAIN_ACTIVITY", "Unable to render tasks")
        }
    }

    fun clearTasks(view: View) {
        try {
            val textView: TextView = findViewById(R.id.textView2)
            textView.setText("")
        } finally {
            Log.e("MAIN_ACTIVITY", "Unable to clear tasks")
        }
    }

    suspend fun uploadAndRenderTasks() {
        val client = HttpClient() {
            install(JsonFeature) {
                serializer = JacksonSerializer()
            }
        }
        try {
            val tasks: TaskListDTO = client.get("http://192.168.0.18:8080/tasks") // TODO: config for domain, and do the same for the .xml secutiry file
            val taskList = tasks.data.joinToString(transform = {it.name}, separator = ",")
            val textView: TextView = findViewById(R.id.textView2)
            textView.setText(taskList)
        } finally {
            client.close()
        }
    }
}