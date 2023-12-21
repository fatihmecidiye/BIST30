package com.example.bist30monitor

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.SimpleDateFormat
import java.util.*

class MyXAxisValueFormatter : ValueFormatter() {
    // Override the getFormattedValue method to format the X-axis values
    override fun getFormattedValue(value: Float): String {
        // Convert timestamp to Date
        val date = Date((value.toLong()) * 1000) // assuming the timestamp is in seconds

        // Format Date to display hours
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("GMT+3") // Set the time zone to GMT+3
        return sdf.format(date)
    }
}

class CustomXAxisValueFormatter : ValueFormatter() {

    override fun getFormattedValue(value: Float): String {
        return when (value) {
            10f -> "10"
            12f -> "12"
            14f -> "14"
            16f -> "16"
            18f -> "18"
            else -> ""
        }
    }
}