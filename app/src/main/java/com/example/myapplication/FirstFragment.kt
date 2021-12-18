package com.example.myapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var curFrom : String? = null
    private var curTo : String? = null

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val listOfCurrencies = arrayOf("CHF", "RUB", "USD", "EUR", "SEK")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArrayAdapter(view.context,
            R.layout.list_view_item, listOfCurrencies)

        val listCurFrom:ListView =  view.findViewById(R.id.id_cur_from)
        val listCurTo:ListView =  view.findViewById(R.id.id_cur_to)
        listCurFrom.setAdapter(adapter)
        listCurTo.setAdapter(adapter)

        listCurTo.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, view: View,
                                     position: Int, id: Long) {
                // value of item that is clicked
                val itemValue = listCurFrom.getItemAtPosition(position) as String
                curTo = itemValue
            }
        }

        listCurFrom.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, view: View,
                                     position: Int, id: Long) {
                // value of item that is clicked
                val itemValue = listCurFrom.getItemAtPosition(position) as String
                curFrom = itemValue
            }
        }

        binding.buttonFirst.setOnClickListener {

           val bundle = Bundle()
            bundle.putString("curFrom", curFrom);
            bundle.putString("curTo", curTo);
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}