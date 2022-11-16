package com.symmetrium.pricecalculation.ui.activities.mainactivity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.symmetrium.pricecalculation.R
import com.symmetrium.pricecalculation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.HashMap


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

        binding.viewModel = viewModel

        viewModel.allCurrencies.observe(this) { it ->
            Log.e("onCreate: ", "onCreate: ")
            populateCurrencies(it)
        }
        viewModel.loadCurrencies()
    }

    private fun populateCurrencies(it: HashMap<String, String>) {
        val arrayAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, it.keys.toList())
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(binding) {

            spinner.adapter = arrayAdapter
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val tutorialsName = parent.getItemAtPosition(position)
                    Toast.makeText(parent.context, "Selected: $tutorialsName", Toast.LENGTH_LONG)
                        .show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }


            spinner2.adapter = arrayAdapter
            spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val tutorialsName = parent.getItemAtPosition(position)
                    Toast.makeText(parent.context, "Selected: $tutorialsName", Toast.LENGTH_LONG)
                        .show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }

    }
}