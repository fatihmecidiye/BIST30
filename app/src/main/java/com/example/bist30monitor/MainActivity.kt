package com.example.bist30monitor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val aselsManager = AselsManager(this) // Pass the reference of the activity
        aselsManager.fetchAndDisplayStockData()
    }
}