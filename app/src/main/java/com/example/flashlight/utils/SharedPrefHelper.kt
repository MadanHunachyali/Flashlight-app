package com.example.flashlight.utils

import android.content.Context

class SharedPrefHelper(context: Context) {

    private val pref = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)

    fun saveUser(email: String, password: String) {
        pref.edit()
            .putString("email", email)
            .putString("password", password)
            .apply()
    }

    fun getEmail(): String? {
        return pref.getString("email", null)
    }

    fun getPassword(): String? {
        return pref.getString("password", null)
    }
}