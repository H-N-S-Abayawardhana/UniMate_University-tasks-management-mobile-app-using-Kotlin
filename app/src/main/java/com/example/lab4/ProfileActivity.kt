package com.example.lab4

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        dbHelper = DBHelper(this)

        // Assuming you have the user's email available
        val userEmail = "example@email.com"

        // Fetch user data from the database
        val user = dbHelper.getUserByEmail(userEmail)

        // Set the user data to the corresponding views
        if (user != null) {
            findViewById<TextView>(R.id.nameTextView).text = user.name ?: "N/A"
            findViewById<TextView>(R.id.emailTextView).text = user.email ?: "N/A"
            findViewById<TextView>(R.id.contactTextView).text = user.contact ?: "N/A"
            findViewById<TextView>(R.id.addressTextView).text = user.address ?: "N/A"
            // It's generally not recommended to show passwords in UI. If needed for debugging, handle securely.
            // findViewById<TextView>(R.id.passwordTextView).text = user.password ?: "N/A"
        } else {
            // Handle the case where the user is not found
            findViewById<TextView>(R.id.nameTextView).text = "User not found"
        }
    }
}
