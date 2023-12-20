package com.example.bist30monitor

data class YahooFinanceResponse(
    val chart: ChartData?,
    val error: String?
)

data class ChartData(
    val result: List<StockResult>?
)

data class StockResult(
    val meta: StockMeta?,
    val indicators: StockIndicators?
)

data class StockMeta(
    val currency: String?,
    val symbol: String?,
    val exchangeName: String?,
    val regularMarketPrice: Double?,
    val previousClose: Double?,
    // Add other relevant fields
)

data class StockIndicators(
    val quote: List<Quote>?
)

data class Quote(
    val close: List<Double>?,
    // Add other relevant fields
)
