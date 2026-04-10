package com.example.flashlight.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.flashlight.R
import com.example.flashlight.ui.home.HomeActivity
import com.example.flashlight.utils.SharedPrefHelper
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val emailEdit = findViewById<TextInputEditText>(R.id.email)
        val passwordEdit = findViewById<TextInputEditText>(R.id.password)
        val loginBtn = findViewById<MaterialButton>(R.id.loginBtn)
        val registerText = findViewById<TextView>(R.id.registerText)

        val helper = SharedPrefHelper(this)

        loginBtn.setOnClickListener {

            val email = emailEdit.text.toString().trim()
            val password = passwordEdit.text.toString().trim()

            val savedEmail = helper.getEmail()
            val savedPassword = helper.getPassword()

            if (email == savedEmail && password == savedPassword) {

                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, HomeActivity::class.java))
                finish()

            } else {
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        }

        registerText.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}