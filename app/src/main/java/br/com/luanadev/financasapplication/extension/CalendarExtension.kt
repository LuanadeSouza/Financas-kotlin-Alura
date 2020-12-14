package br.com.luanadev.financasapplication.extension

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.formataDataParaBrasileiro(): String {
    val formatoBrasileiro = "dd/MM/yyyy"
    val format = SimpleDateFormat(formatoBrasileiro)
    return format.format(time)
}
