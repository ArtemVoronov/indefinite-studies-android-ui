package com.indefinitestudies.app

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.indefinitestudies.app.services.Services

// TODO: implement simple UI CRUD
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun renderTasks(view: View) {
        try {
            Services.TASKS_SERVICE.getTaskList(
                onResponse = { tasks ->
                    val taskList = tasks.data.joinToString(transform = { it.name }, separator = ",")
                    val textView: TextView = findViewById(R.id.textView2)
                    textView.setText(taskList)
                }
            )
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

}