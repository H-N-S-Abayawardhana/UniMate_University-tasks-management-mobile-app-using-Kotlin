package com.example.lab4

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent

class StartActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start3)

        findViewById<Button>(R.id.finishButton).setOnClickListener {
            // Perform any final steps here. This could be saving data or returning to a main activity
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()  // Close this activity
        }
    }
}
