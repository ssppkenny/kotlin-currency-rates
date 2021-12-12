package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.api.CurrencyRatesApiService
import com.example.myapplication.databinding.FragmentSecondBinding
import com.example.myapplication.impl.CurrencyRatesApiServiceImpl
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.util.*
import java.util.concurrent.Executors

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private var curFrom : String? = null;
    private var curTo : String? = null;

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        curFrom = arguments?.getString("curFrom")
        curTo = arguments?.getString("curTo")
        val currencyRatesApiServiceImpl = CurrencyRatesApiServiceImpl()

        val ratesTask = RatesTask(this)
        val cal = GregorianCalendar()
        ratesTask.execute(Parameters(curFrom, curTo, cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR)))


    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun plot(data : List<Double>) {

        val entries = ArrayList<Entry>()
        data.forEachIndexed { i, e ->
            entries.add(Entry(i.toFloat(), e.toFloat()))
        }
        val dataset = LineDataSet(entries, "Rates")
        val chart = getView()?.findViewById<LineChart>(R.id.ratesChart);

        chart!!.data = LineData(listOf(dataset))
        chart.invalidate()
    }
}