package com.example.myapplication.impl

import com.example.myapplication.api.CurrencyRatesApiService
import tech.tablesaw.api.Table
import java.lang.Exception
import java.util.concurrent.Callable


class CallableRequest(
    private val service: CurrencyRatesApiService,
    private val curFrom: String?,
    private val curTo: String?,
    private val day: Int,
    private val month: Int,
    private val year: Int
) :
    Callable<String?> {
    @Throws(Exception::class)
    override fun call(): String? {
        return service.getRates(curFrom, curTo, day, month, year)
    }
}