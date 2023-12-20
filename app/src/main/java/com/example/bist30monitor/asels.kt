package com.example.bist30monitor

import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// ...

// Define a function to fetch stock data using a coroutine scope
/*fun fetchStockData() {
    // Coroutine scope
    CoroutineScope(Dispatchers.IO).launch {
        val service = NetworkService.yahooFinanceApi
        val response = service.getStockData("asels.is")
        // Handle the response as needed
        if (response.error.isNullOrEmpty()) {
            val result = response.chart?.result?.get(0)
            val meta = result?.meta
            val indicators = result?.indicators?.quote?.get(0)

            val name = meta?.symbol ?: "N/A"
            val price = indicators?.close?.get(0) ?: 0.0

            // Now 'name' and 'price' contain the extracted data
        } else {
            // Handle error case
        }
    }
}*/


class AselsManager(private val context: AppCompatActivity) {
    // Your existing code for fetching stock data
    // ...

    // Function to update UI with retrieved data
    private fun displayStockData(name: String, price: Double) {
        // Get references to TextViews from the layout
        val textViewName = context.findViewById<TextView>(R.id.textViewName)
        val textViewPrice = context.findViewById<TextView>(R.id.textViewPrice)

        // Set the retrieved data to the TextViews
        textViewName.text = "Stock Name: $name"
        textViewPrice.text = "Stock Price: $price"
    }

    // Function to fetch stock data and update UI
    fun fetchAndDisplayStockData() {
        CoroutineScope(Dispatchers.IO).launch {
            val service = NetworkService.yahooFinanceApi
            val response = service.getStockData("asels.is")

            // Handle the response and extract data
            if (response.error.isNullOrEmpty()) {
                val result = response.chart?.result?.get(0)
                val meta = result?.meta
                val indicators = result?.indicators?.quote?.get(0)

                val name = meta?.symbol ?: "N/A"
                val price = indicators?.close?.get(0) ?: 0.0

                // Update UI from the UI thread
                context.runOnUiThread {
                    displayStockData(name, price)
                }
            } else {
                // Handle error case
            }
        }
    }
}