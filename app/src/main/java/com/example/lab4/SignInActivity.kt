package com.example.lab4

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignInActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        dbHelper = DBHelper(this)

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val signInButton = findViewById<Button>(R.id.signInButton)
        val signUpLink = findViewById<TextView>(R.id.signUpLink)

        signInButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email and password cannot be empty.", Toast.LENGTH_SHORT).show()
            } else {
                val db = dbHelper.readableDatabase
                val cursor: Cursor = db.rawQuery("SELECT * FROM users WHERE email=? AND password=?", arrayOf(email, password))

                if (cursor.moveToFirst()) {
                    Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                    // Navigate to DashboardActivity after successful login
                    startActivity(Intent(this, DashboardActivity::class.java))
                    finish() // Optional: Finish the current activity after navigating
                } else {
                    Toast.makeText(this, "Invalid email or password.", Toast.LENGTH_SHORT).show()
                }

                cursor.close()
            }
        }

        // Set an OnClickListener for the Sign Up link
        signUpLink.setOnClickListener {
            // Start SignUpActivity
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}
