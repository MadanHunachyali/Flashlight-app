package com.example.flashlight.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.flashlight.R
import com.example.flashlight.utils.SharedPrefHelper
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_signup)

        val emailEdit = findViewById<TextInputEditText>(R.id.email)
        val passwordEdit = findViewById<TextInputEditText>(R.id.password)
        val confirmEdit = findViewById<TextInputEditText>(R.id.confirmPassword)
        val registerBtn = findViewById<MaterialButton>(R.id.registerBtn)

        val helper = SharedPrefHelper(this)

        registerBtn.setOnClickListener {

            val email = emailEdit.text.toString().trim()
            val password = passwordEdit.text.toString().trim()
            val confirm = confirmEdit.text.toString().trim()

            if (email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
            }
            else if (password != confirm) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
            else {
                helper.saveUser(email, password)

                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }
}