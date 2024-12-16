package com.example.ecommerceapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Delay for 3 seconds and navigate to the sign-in page (or MainActivity if needed)
        Handler().postDelayed({
            // Change the target activity here
            val intent = Intent(this, Activity_sign_in::class.java) // or Activity_sign_up
            startActivity(intent)
            finish() // Close the SplashScreen activity so it cannot be returned to
        }, 3000) // 3000 milliseconds = 3 seconds
    }
}
