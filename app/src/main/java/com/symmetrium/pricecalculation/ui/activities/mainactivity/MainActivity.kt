package com.symmetrium.pricecalculation.ui.activities.mainactivity

import android.R.string
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.symmetrium.pricecalculation.R
import dagger.hilt.android.AndroidEntryPoint
import java.util.HashMap


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.allCurrencies.observe(this) { it ->

            Log.e("onCreate: ", "onCreate: ")
            popuateCurrencies(it)
        }
        viewModel.loadCurrencies()
    }

    private fun popuateCurrencies(it: HashMap<String, String>?) {
//        val arrayAdapter: ArrayAdapter<String> =
//            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList)
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//
//        spinner.setAdapter(arrayAdapter)
//        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//                val tutorialsName: string = parent.getItemAtPosition(position).toString()
//                Toast.makeText(parent.context, "Selected: $tutorialsName", Toast.LENGTH_LONG)
//                    .show()
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//        })
    }
}