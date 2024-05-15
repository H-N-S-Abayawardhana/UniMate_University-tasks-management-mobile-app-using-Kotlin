package com.example.lab4

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        dbHelper = DBHelper(this)

        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val contactEditText = findViewById<EditText>(R.id.contactEditText)
        val addressEditText = findViewById<EditText>(R.id.addressEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val confirmPasswordEditText = findViewById<EditText>(R.id.confirmPasswordEditText)
        val signUpButton = findViewById<Button>(R.id.signUpButton)
        val signInLink = findViewById<TextView>(R.id.signInLink)

        signUpButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val contact = contactEditText.text.toString().trim()
            val address = addressEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || contact.isEmpty() || address.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!isValidContactNumber(contact)) {
                Toast.makeText(this, "Please enter a valid 10-digit contact number.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!isValidEmail(email)) {
                Toast.makeText(this, "Please enter a valid email address.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (containsDigit(name)) {
                Toast.makeText(this, "Name cannot contain digits.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val values = ContentValues()
            values.put("name", name)
            values.put("email", email)
            values.put("contact", contact)
            values.put("address", address)
            values.put("password", password)  // Remember to hash the password in a real app

            val db = dbHelper.writableDatabase
            db.insert("Users", null, values)
            Toast.makeText(this, "User registered successfully!", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }

        // Set click listener on the sign in link
        signInLink.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }

    private fun isValidContactNumber(contact: String): Boolean {
        return contact.length == 10 && contact.matches(Regex("\\d+"))
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun containsDigit(input: String): Boolean {
        return input.any { it.isDigit() }
    }
}
