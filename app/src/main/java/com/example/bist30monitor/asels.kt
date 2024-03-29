package com.example.bist30monitor

import android.content.Intent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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


/*class AselsManager(private val context: AppCompatActivity) {
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
}*/

class AselsManager(private val context: AppCompatActivity) {

    val stockList = mutableListOf<Stock>()
    // Function to fetch stock data and update UI for multiple stocks
    fun fetchAndDisplayMultipleStocks() {
        val stockNames = listOf(
            "akbnk", "alark", "arclk", "asels", "astor",
            "bimas", "ekgyo", "enkai", "eregl", "froto",
            "garan", "gubrf", "hekts", "isctr", "kchol",
            "kontr", "kozal", "krdmd", "odas", "oyakc",
            "petkm", "pgsus", "sahol", "sasa", "sise",
            "tcell", "thyao", "toaso", "tuprs", "ykbnk"
        )


        val recyclerView: RecyclerView = context.findViewById(R.id.recyclerView)

        // Set up RecyclerView with an adapter
        val adapter = StockAdapter(stockList)

        adapter.setOnStockClickListener(object : StockAdapter.OnStockClickListener {
            override fun onStockClick(stock: Stock) {
                // Start the detail activity and pass relevant data
                val intent = Intent(context, StockDetailActivity::class.java)
                intent.putExtra("STOCK_NAME", stock.name)
                // Add other relevant data as extras to the intent
                startActivity(context, intent, null)
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        // Fetch data for each stock
        CoroutineScope(Dispatchers.IO).launch {
            val service = NetworkService.yahooFinanceApi

            stockNames.forEach { stockName ->
                val response = service.getStockData("$stockName.is")

                // Handle the response and extract data
                if (response.error.isNullOrEmpty()) {
                    val result = response.chart?.result?.get(0)
                    val meta = result?.meta
                    val indicators = result?.indicators?.quote?.get(0)

                    val name = meta?.symbol ?: "N/A"
                    val price = meta?.regularMarketPrice ?: 0.0
                    var dailyChange = ( price - (meta?.previousClose ?: 0.0)) / (meta?.previousClose?: 1.0) * 100

                    // Update UI on the main thread
                    withContext(Dispatchers.Main) {
                        val cleanName = name?.removeSuffix(".IS") ?: "N/A"
                        val formattedPrice = String.format("%.2f", price)
                        val formattedPriceDouble = formattedPrice.toDoubleOrNull() ?: 0.0
                        var formattedDailyChange = String.format("%.2f", dailyChange).toDoubleOrNull() ?: 0.0
                        val stock = Stock(cleanName, formattedPriceDouble, formattedDailyChange)
                        stockList.add(stock)
                        adapter.notifyDataSetChanged()
                    }
                } else {
                    // Handle error case for individual stock
                }
            }
        }
    }

    @JvmName("getStockList1")
    fun getStockList(): MutableList<Stock> {
        return stockList
    }


}
