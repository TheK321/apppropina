package com.isc.apppropina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.isc.apppropina.databinding.ActivityMainBinding
import com.isc.apppropina.databinding.ActivityResumenBinding
import java.text.NumberFormat

class Resumen : AppCompatActivity() {
    private lateinit var binding: ActivityResumenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResumenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var bundle = intent.extras
        val propina: Double? = bundle?.getDouble("propina")
        val redondear: Boolean? = bundle?.getBoolean("redondear")
        val costo: String? = bundle?.getString("costo")
        calculateTip(propina, redondear, costo)
    }

    private fun calculateTip(propina: Double?, redondear: Boolean?, costo: String?) {
        val cost = costo?.toDoubleOrNull()
        if (cost == null) {
            binding.txtvwPropina.text = ""
            return
        }
        var tip = propina?.times(cost)
        if (redondear == true) {
            tip = tip?.let { kotlin.math.ceil(it) }
        }
        when (propina) {
            0.20 -> binding.txtvwMensaje.text =
                "Nos alegra que su experiencia haya sido de lo mejor, trabajamos para garantizar un servicio de excelencia."
            0.18 -> binding.txtvwMensaje.text =
                "Gracias por su valoración, nos encantaría escuchar sus comentarios para saber en qué podemos mejorar."
            else -> binding.txtvwMensaje.text =
                "Trabajaremos para brindarle un mejor servicio en su próxima visita, gracias por su preferencia. "
        }
        var total = tip?.plus(cost)
        var formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.txtvwTotal.text = tip.toString()
        binding.txtvwPropina.text = getString(R.string.txtPropina, formattedTip)
        formattedTip = NumberFormat.getCurrencyInstance().format(total)
        binding.txtvwTotal.text = getString(R.string.txtTotal, formattedTip)
    }
}