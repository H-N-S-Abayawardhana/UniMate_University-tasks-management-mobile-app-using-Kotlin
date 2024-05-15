package com.example.lab4

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

class ProfileActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        dbHelper = DBHelper(this)

        // Assuming you have the user's email available from a previous activity or login process
        val userEmail = "example@email.com"
        Log.d("ProfileActivity", "User email: $userEmail")

        // Fetch user data from the database
        val user = dbHelper.getUserByEmail(userEmail)

        // Set the user data to the corresponding EditText views
        if (user != null) {
            findViewById<EditText>(R.id.nameEditText).setText(user.name)
            findViewById<EditText>(R.id.emailEditText).setText(user.email)
            findViewById<EditText>(R.id.contactEditText).setText(user.contact)
            findViewById<EditText>(R.id.addressEditText).setText(user.address)
        } else {
            // Handle the case where the user is not found
            Log.e("ProfileActivity", "User not found!")
        }
    }
}
