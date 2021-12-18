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

    fun minusOneMonth(day: Int?, month: Int?, year:Int?) : Triple<Int,Int,Int> {
        if (month == 1) {
            return Triple(day!!, 12, year!! - 1)
        }
        return Triple(day!!,month!! - 1,year!!)
    }

    override fun getRates(
        curFrom: String?,
        curTo: String?,
        day: Int?,
        month: Int?,
        year: Int?
    ): String? {
        val d = String.format("%02d", day)
        val m = String.format("%02d", month)
        val t = minusOneMonth(day, month, year)

        val m1 = String.format("%02d", t.second)
        val callRates: Call<ResponseBody?>? =
            api.getRates(curFrom, curTo, d, d, m1, m, t.third, year!!)
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