package com.example.lab4

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent

class StartActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start2)

        val nextButton: Button = findViewById(R.id.nextbutton2)
        nextButton.setOnClickListener {
            // Start StartActivity3 when the Next button is clicked
            val intent = Intent(this, StartActivity3::class.java)
            startActivity(intent)
        }
    }
}
