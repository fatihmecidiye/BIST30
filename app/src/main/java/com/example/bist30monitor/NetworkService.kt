package com.example.bist30monitor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {
    private const val BASE_URL = "https://query1.finance.yahoo.com/v8/finance/chart/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val yahooFinanceApi: YahooFinanceApiService = retrofit.create(YahooFinanceApiService::class.java)
}