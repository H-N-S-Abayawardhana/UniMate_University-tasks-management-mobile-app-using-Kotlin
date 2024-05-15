package com.example.lab4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewTasksActivity : AppCompatActivity(), TaskAdapter.OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewtasks)
        recyclerView = findViewById(R.id.recyclerView)
        dbHelper = DBHelper(this)

        // Retrieve tasks from the database
        val taskList = dbHelper.getAllTasks()

        // Set up the RecyclerView adapter
        val adapter = TaskAdapter(taskList, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onDeleteClick(task: Task) {
        dbHelper.deleteTask(task.id)
        refreshTaskList()
    }

    private fun refreshTaskList() {
        val taskList = dbHelper.getAllTasks()
        val adapter = TaskAdapter(taskList, this)
        recyclerView.adapter = adapter
    }
}