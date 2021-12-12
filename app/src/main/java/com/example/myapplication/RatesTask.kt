package com.example.myapplication

import android.os.AsyncTask
import com.example.myapplication.impl.CurrencyRatesApiServiceImpl

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements


class RatesTask(val fragment : SecondFragment)  : AsyncTask<Parameters, Void, String?>() {

    override fun doInBackground(vararg parameters: Parameters?): String? {

        val params = parameters.get(0);
        val service = CurrencyRatesApiServiceImpl()
        return service.getRates(params?.curFrom, params?.curTo, params?.day, params?.month, params?.year)

    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        val document: Document = Jsoup.parse(result)

        val tables: Elements = document.select("table[border='1']")
        val table: Element = tables.get(0)
        val rows: Elements = table.select("tr")
        val values: MutableList<Double> = ArrayList()
        for (j in 2 until rows.size) {
            val columns: Elements = rows.get(j).select("td")
            values.add(java.lang.Double.valueOf(columns.get(1).text()))
        }

        println(values)

        fragment.plot(values)

    }

}