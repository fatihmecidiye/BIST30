package com.example.bist30monitor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val aselsManager = AselsManager(this) // Pass the reference of the activity
        // Call fetchAndDisplayStockData function from AselsManager
        aselsManager.fetchAndDisplayMultipleStocks()

    }
}