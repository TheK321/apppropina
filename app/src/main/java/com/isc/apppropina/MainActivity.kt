package com.isc.apppropina

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.isc.apppropina.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Mario Martínez Arteaga
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            val intent = (Intent(this, Resumen::class.java))
            val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
                R.id.option_twenty_percent -> 0.20
                R.id.option_eighteen_percent -> 0.18
                else -> 0.15
            }
            val b:Bundle = Bundle()
            b.putDouble("propina",tipPercentage)
            b.putBoolean("redondear",binding.roundUpSwitch.isChecked)
            b.putString("costo",binding.costoServicio.text.toString())
            intent.putExtras(b)
            startActivity(intent)
        }
    }
}