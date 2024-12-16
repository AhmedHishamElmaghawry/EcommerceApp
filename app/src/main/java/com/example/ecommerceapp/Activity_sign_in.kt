package com.example.ecommerceapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Activity_sign_in : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // UI Elements
        val signUpText: TextView = findViewById(R.id.signUpText)
        val signInButton: Button = findViewById(R.id.signInButton)
        val emailField: EditText = findViewById(R.id.emailEditText)
        val passwordField: EditText = findViewById(R.id.passwordEditText)

        // Check if a user is already signed in
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // User is signed in, navigate to the MainActivity
            Toast.makeText(this, "Welcome back, ${currentUser.email}", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java)) // Navigate to the main activity
            finish() // Close the sign-in activity
        }

        // Navigate to SignUpActivity when Sign Up link is clicked
        signUpText.setOnClickListener {
            val intent = Intent(this, Activity_sign_up::class.java)
            startActivity(intent)
        }

        // Handle Sign In button click
        signInButton.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Sign in with email and password
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Redirect to MainActivity on successful sign-in
                            Toast.makeText(this, "Sign-in successful!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish() // Close the sign-in activity
                        } else {
                            // Show error message if sign-in failed
                            Toast.makeText(this, "Sign-in failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }
    }
}
