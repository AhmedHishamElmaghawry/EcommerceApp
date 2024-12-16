// Activity_sign_up.kt
package com.example.ecommerceapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest

class Activity_sign_up : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // UI Elements
        val signInText: TextView = findViewById(R.id.signInText)
        val signUpButton: Button = findViewById(R.id.signUpButton)
        val emailField: EditText = findViewById(R.id.emailEditText)
        val passwordField: EditText = findViewById(R.id.passwordEditText)
        val nameField: EditText = findViewById(R.id.nameEditText) // Ensure you have a name field

        // Navigate to SignInActivity when Sign In link is clicked
        signInText.setOnClickListener {
            val intent = Intent(this, Activity_sign_in::class.java)
            startActivity(intent)
        }

        // Handle Sign Up button click
        signUpButton.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()
            val name = nameField.text.toString().trim()

            if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Set the user's display name
                            val user: FirebaseUser? = auth.currentUser
                            val profileUpdates = UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .build()
                            user?.updateProfile(profileUpdates)?.addOnCompleteListener { updateTask ->
                                if (updateTask.isSuccessful) {
                                    // Registration successful, navigate to SignInActivity
                                    Toast.makeText(this, "Sign-up successful. Please log in.", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this, Activity_sign_in::class.java))
                                    finish()
                                } else {
                                    // Display name update failed
                                    Toast.makeText(this, "Failed to set display name: ${updateTask.exception?.message}", Toast.LENGTH_LONG).show()
                                }
                            }
                        } else {
                            // Registration failed, show error message
                            Toast.makeText(this, "Sign-up failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }
    }
}
