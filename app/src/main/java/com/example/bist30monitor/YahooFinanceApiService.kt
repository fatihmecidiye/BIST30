package com.example.bist30monitor

import retrofit2.http.GET
import retrofit2.http.Path

interface YahooFinanceApiService {
    @GET("{stockName}")
    suspend fun getStockData(@Path("stockName") stockName: String): YahooFinanceResponse
}