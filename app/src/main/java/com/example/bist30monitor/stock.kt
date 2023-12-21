package com.example.bist30monitor

import java.sql.Timestamp

data class Stock(
    val name: String,
    var price: Double,
    var dailyChange: Double
)

