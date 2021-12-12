package com.example.myapplication.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import tech.tablesaw.api.Table

interface CurrencyRatesApiService {
    fun getRates(curFrom: String?, curTo: String?, month: Int?, day: Int?, year: Int?): String?
    interface Api {
        // A=1&C1=CHF&C2=RUB&TR=1&DD1=19&MM1=07&YYYY1=2019&B=1&P=&I=1&DD2=19&MM2=07&YYYY2=2020&btnOK=Go%21
        @GET("/en/historical-exchange-rates.php?A=1&TR=1&B=1&P=&I=1&btnOK=Go%21")
        fun getRates(
            @Query("C1") curFrom: String?,
            @Query("C2") curTo: String?,
            @Query("DD1") dayFrom: String?,
            @Query("DD2") dayTo: String?,
            @Query("MM1") monthFrom: String?,
            @Query("MM2") monthTo: String?,
            @Query("YYYY1") yearFrom: Int,
            @Query("YYYY2") yearTo: Int
        ): Call<ResponseBody?>?
    }
}
