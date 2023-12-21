package com.example.bist30monitor

data class YahooFinanceDetailResponse(
    val chart: Chart,
    val error: String?
)

data class Chart(
    val result: List<Result>
)

data class Result(
    val meta: Meta,
    val timestamp: List<Long>,
    val indicators: Indicators,
)

data class Meta(
    val currency: String,
    val symbol: String,
    val exchangeName: String,
    val instrumentType: String,
    val firstTradeDate: Long,
    val regularMarketTime: Long,
    val gmtoffset: Long,
    val timezone: String,
    val exchangeTimezoneName: String,
    val regularMarketPrice: Double,
    val chartPreviousClose: Double,
    val previousClose: Double,
    val scale: Long,
    val priceHint: Long,
    val currentTradingPeriod: CurrentTradingPeriod,
    val tradingPeriods: List<List<TradingPeriod>>,
    val dataGranularity: String,
    val range: String,
    val validRanges: List<String>,
)

data class CurrentTradingPeriod(
    val pre: Pre,
    val regular: Regular,
    val post: Post,
)

data class Pre(
    val timezone: String,
    val start: Long,
    val end: Long,
    val gmtoffset: Long,
)

data class Regular(
    val timezone: String,
    val start: Long,
    val end: Long,
    val gmtoffset: Long,
)

data class Post(
    val timezone: String,
    val start: Long,
    val end: Long,
    val gmtoffset: Long,
)

data class TradingPeriod(
    val timezone: String,
    val start: Long,
    val end: Long,
    val gmtoffset: Long,
)

data class Indicators(
    val quote: List<Quote2>,
)

data class Quote2(
    val close: List<Double?>,
    val low: List<Double?>,
    val high: List<Double?>,
    val volume: List<Long?>,
    val open: List<Double?>,
)