package com.example.bitcoinconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bitcoinconverter.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.convertButton.setOnClickListener {
            convertAmount()
        }
    }

    private fun convertAmount() {
        val amount = binding.enterAmount.text.toString().toDoubleOrNull()
        if (amount == null || amount == 0.0) {
            displayResult(0.0)
            return
        }

        var btcRate = (amount / 492.20) / 49349.52

        val roundUp = binding.roundupSwitch.isChecked
        if (roundUp) {
            btcRate = btcRate.let { kotlin.math.round(it) }

        }

        displayResult(btcRate)

    }

    private fun displayResult(btcRate: Double) {
        val formattedResult = NumberFormat.getNumberInstance().format(btcRate)
        binding.btcValue.text = getString(R.string.btc_value, formattedResult)
    }

}