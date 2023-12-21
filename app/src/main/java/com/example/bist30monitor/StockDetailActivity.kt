package com.example.bist30monitor
import com.github.mikephil.charting.data.Entry
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Half.toFloat
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StockDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_detail)

        val stockName = intent.getStringExtra("STOCK_NAME")
        CoroutineScope(Dispatchers.IO).launch {
            val service = NetworkService.yahooFinanceApi
            // Retrieve other relevant data from intent extras
            val response = service.getStockDetailData("$stockName.is")

            // Handle the response and extract data
            if (response.error.isNullOrEmpty()) {
                val result = response.chart?.result?.get(0)
                val timestamps = result?.timestamp ?: emptyList()
                val prices = result?.indicators?.quote?.get(0)?.close ?: emptyList()

                val entries = ArrayList<Entry>()

                for (i in timestamps.indices) {
                    val entry = Entry(timestamps[i].toFloat(), ((prices[i] ?: (prices[i+4] ?: 0)).toFloat()))
                    entries.add(entry)
                }

                val dataSet = LineDataSet(entries, "Fiyat")
                val lineData = LineData(dataSet)

                // Customize the line chart appearance
                dataSet.color = resources.getColor(R.color.chart_line_color)
                // Further customization options...

                // Assuming your LineChart in XML layout has the id "lineChart"
                val lineChart: LineChart = findViewById(R.id.lineChart)

                // Update UI (HeaderTextView) on the main/UI thread
                withContext(Dispatchers.Main) {
                    val headerTextView: TextView = findViewById(R.id.headerTextView)
                    headerTextView.text = stockName// Replace with your stock name variable
                }
                // Set the chart data
                runOnUiThread {
                    lineChart.data = lineData
                    lineChart.description.text = ""
                    // Customize X-axis values
                    val xAxis = lineChart.xAxis
                    xAxis.valueFormatter = MyXAxisValueFormatter() // Custom X-axis formatter
                    xAxis.granularity = 2f // Set interval to 2 hours
                    xAxis.isGranularityEnabled = true

                    lineChart.invalidate() // Refresh the chart
                }

            } else {
                // Handle error case for individual stock
            }
        }
        // Display the stock details in the detail activity
        // You can use stockName and other retrieved data to populate the UI
    }
}


