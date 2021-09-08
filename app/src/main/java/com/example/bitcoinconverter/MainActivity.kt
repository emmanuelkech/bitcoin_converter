package com.example.bitcoinconverter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
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

        binding.enterAmountLayout.setOnKeyListener { view, keyCode, _ -> keyEvent(view, keyCode) }
    }

    private fun convertAmount() {
        val amount = binding.enterAmount.text.toString().toDoubleOrNull()
        if (amount == null || amount == 0.0) {
            displayResult(0.0)
            return
        }

        val btcRate = (amount / 492.20) / 49349.52

        var btcRates = String.format("%.2f", btcRate).toDouble() //formats result to 2dp default

        val roundUp = binding.roundupSwitch.isChecked
        if (roundUp) {
            btcRates = btcRates.let { kotlin.math.round(it) }

        }

        displayResult(btcRates)

    }

    private fun displayResult(btcRate: Double) {
        val formattedResult = NumberFormat.getNumberInstance().format(btcRate)
        binding.btcValue.text = getString(R.string.btc_value, formattedResult)
    }

    private fun keyEvent(view: View, keyCode : Int) : Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER){
            //hide the keyboard
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

}