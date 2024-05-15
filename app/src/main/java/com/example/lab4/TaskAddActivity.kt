package com.example.lab4

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class TaskAddActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper
    private lateinit var titleEditText: EditText
    private lateinit var typeSpinner: Spinner
    private lateinit var moduleEditText: EditText
    private lateinit var dateEditText: EditText
    private lateinit var descriptionEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_taskadd)
        dbHelper = DBHelper(this)
        titleEditText = findViewById(R.id.titleEditText)
        typeSpinner = findViewById(R.id.typeSpinner)
        moduleEditText = findViewById(R.id.moduleEditText)
        dateEditText = findViewById(R.id.dateEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)
        val addTaskButton: Button = findViewById(R.id.addTaskButton)

        // Populate spinner with task types
        val taskTypes = arrayOf("Assignment", "Project", "Exam")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, taskTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        typeSpinner.adapter = adapter

        addTaskButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val type = typeSpinner.selectedItem.toString()
            val module = moduleEditText.text.toString()
            val date = dateEditText.text.toString()
            val description = descriptionEditText.text.toString()

            if (title.isNotEmpty() && module.isNotEmpty() && date.isNotEmpty() && description.isNotEmpty()) {
                dbHelper.addTask(title, type, module, date, description)
                showToast("Task added successfully!")

                // Start the ViewTasksActivity
                val intent = Intent(this@TaskAddActivity, ViewTasksActivity::class.java)
                startActivity(intent)
                finish() // Optional: Finish the current activity
            } else {
                showToast("Please fill in all fields")
            }
        }

        dateEditText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(this, { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                dateEditText.setText(selectedDate)
            }, year, month, day)
            datePickerDialog.show()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
