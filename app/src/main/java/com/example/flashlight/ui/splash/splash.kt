package com.example.flashlight.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.flashlight.R
import android.os.Handler
import android.os.Looper
import android.content.Intent
import com.example.flashlight.ui.auth.LoginActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({

            startActivity(Intent(this, LoginActivity::class.java))
            finish()

        }, 2000)
    }
}