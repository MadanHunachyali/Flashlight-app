package com.example.flashlight.ui.home

import android.content.Intent
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flashlight.R
import com.example.flashlight.adapter.LogsAdapter
import com.example.flashlight.utils.TimeUtils
import com.google.android.material.button.MaterialButton
import com.google.android.material.materialswitch.MaterialSwitch

class   HomeActivity : AppCompatActivity() {

    private lateinit var cameraManager: CameraManager
    private lateinit var cameraId: String

    private val logList = mutableListOf<String>()
    private lateinit var adapter: LogsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        val torchSwitch = findViewById<MaterialSwitch>(R.id.torchSwitch)
        val logBtn = findViewById<MaterialButton>(R.id.logBtn)
        val recyclerView = findViewById<RecyclerView>(R.id.logRecyclerView)

        // Setup RecyclerView
        adapter = LogsAdapter(logList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager

        val cameraList = cameraManager.cameraIdList
        if (cameraList.isNotEmpty()) {
            cameraId = cameraList[0]
        }

        torchSwitch.setOnCheckedChangeListener { _, isChecked ->

            try {
                cameraManager.setTorchMode(cameraId, isChecked)

                val status = if (isChecked) "Torch ON" else "Torch OFF"
                val time = TimeUtils.getCurrentTime()

                // Add log
                logList.add("$status at $time")

                // Update UI
                adapter.notifyDataSetChanged()

                Toast.makeText(this, status, Toast.LENGTH_SHORT).show()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // button can scroll to logs
        logBtn.setOnClickListener {

            if (logList.isEmpty()) {
                Toast.makeText(this, "No logs yet", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (recyclerView.visibility == View.GONE) {
                recyclerView.visibility = View.VISIBLE
                recyclerView.smoothScrollToPosition(logList.size - 1)
                logBtn.text = "Hide Logs"   // optional UX
            } else {
                recyclerView.visibility = View.GONE
                logBtn.text = "Show Logs"   // optional UX
            }
        }
    }
}