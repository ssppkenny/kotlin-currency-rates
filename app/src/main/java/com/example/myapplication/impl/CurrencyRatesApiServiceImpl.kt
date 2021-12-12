package com.example.myapplication.impl

import tech.tablesaw.api.DoubleColumn


import okhttp3.ResponseBody

import retrofit2.Retrofit

import com.example.myapplication.api.CurrencyRatesApiService
import retrofit2.Call
import retrofit2.Response
import java.io.IOException


class CurrencyRatesApiServiceImpl : CurrencyRatesApiService {
    private val api: CurrencyRatesApiService.Api
    override fun getRates(
        curFrom: String?,
        curTo: String?,
        day: Int?,
        month: Int?,
        year: Int?
    ): String? {
        val d = String.format("%02d", day)
        val m = String.format("%02d", month)
        val callRates: Call<ResponseBody?>? =
            api.getRates(curFrom, curTo, d, d, m, m, year!!.minus(1), year)
        try {
            val execute = callRates?.execute()
            if (execute!!.isSuccessful) {
                val body = execute.body()
                val s = body!!.string()
                return s

            }
        } catch (e: Exception) {
            return null
       }
        return null
    }

    init {
        api = Retrofit.Builder().baseUrl("https://fxtop.com/en/historical-exchange-rates.php/")
            .build().create(
                CurrencyRatesApiService.Api::class.java
            )
    }
}