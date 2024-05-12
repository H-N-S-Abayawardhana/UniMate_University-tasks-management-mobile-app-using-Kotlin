package com.example.lab4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        // Find the button by its ID and set a click listener
        findViewById<Button>(R.id.nextbutton).setOnClickListener {
            // Start the SignInActivity when the Next button is clicked
            startActivity(Intent(this, SignInActivity::class.java))
        }
        findViewById<Button>(R.id.nextbutton).setOnClickListener {
            startActivity(Intent(this, StartActivity2::class.java))
        }
        findViewById<Button>(R.id.signInButton).setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }


    }
}
