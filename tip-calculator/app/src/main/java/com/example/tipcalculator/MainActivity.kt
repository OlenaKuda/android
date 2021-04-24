package com.example.tipcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton?.setOnClickListener { calculateTip() }

        displayTip(0.0)
    }

    private fun calculateTip() {
        val stringCost = binding.costOfService.text.toString()
        val cost = stringCost.toDoubleOrNull()
        if (cost == null || cost == 0.0) {
            displayTip(0.0)
            return
        }
        val tipPercentage =
            when (binding.tipOptions?.checkedRadioButtonId) {
                R.id.option_twenty_percent -> 0.20
                R.id.option_fifteen_percent -> 0.15
                else -> 0.10
            }
        var tip = cost * tipPercentage
        if (binding.roundUpSwitch?.isChecked!!) {
            tip = kotlin.math.ceil(tip)
        }
        displayTip(tip)
    }

    private fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult?.text = getString(R.string.tip_amount, formattedTip)
    }
}
