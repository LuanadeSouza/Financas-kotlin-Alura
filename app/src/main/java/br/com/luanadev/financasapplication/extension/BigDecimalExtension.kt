package br.com.luanadev.financasapplication.extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

fun BigDecimal.formataParaBrasileiro() : String{
    val numberFormat = DecimalFormat.getCurrencyInstance(Locale("pt", "br"))
    return numberFormat.format(this)
}
