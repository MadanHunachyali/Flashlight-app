package com.example.flashlight.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flashlight.R
import com.example.flashlight.adapter.LogsAdapter

class LogsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_logs)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val logs = intent.getStringArrayListExtra("logs") ?: arrayListOf()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = LogsAdapter(logs)
    }
}