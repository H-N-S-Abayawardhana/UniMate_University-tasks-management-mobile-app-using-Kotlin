package com.example.lab4

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Initialize the Add Task button and set the OnClickListener
        val addButton = findViewById<LinearLayout>(R.id.addSection)
        addButton.setOnClickListener {
            // Create an intent to start TaskAddActivity
            val intent = Intent(this, TaskAddActivity::class.java)
            startActivity(intent) // Start the TaskAddActivity
        }

        // Initialize the Tasks section and set the OnClickListener
        val tasksSection = findViewById<LinearLayout>(R.id.tasksSection)
        tasksSection.setOnClickListener {
            // Create an intent to start ViewTasksActivity
            val intent = Intent(this, ViewTasksActivity::class.java)
            startActivity(intent) // Start the ViewTasksActivity
        }
        // Initialize the Profile section and set the OnClickListener
        val profileSection = findViewById<LinearLayout>(R.id.profileSection)
        profileSection.setOnClickListener {
            // Create an intent to start ProfileActivity
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}