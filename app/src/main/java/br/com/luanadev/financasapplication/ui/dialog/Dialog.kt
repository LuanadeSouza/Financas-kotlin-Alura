package br.com.luanadev.financasapplication.ui.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import br.com.luanadev.financasapplication.R
import br.com.luanadev.financasapplication.delegate.TransacaoDelegate
import br.com.luanadev.financasapplication.extension.covertePraCalendar
import br.com.luanadev.financasapplication.extension.formataDataParaBrasileiro
import br.com.luanadev.financasapplication.model.Tipo
import br.com.luanadev.financasapplication.model.Transacao
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

class Dialog(private val viewGroup: ViewGroup, private val context: Context) {

    private val viewCriada = criaLayout()

    fun configuraDiolog(transacaoDelegate: TransacaoDelegate) {

        configuraCampoData()
        configuraCampoCategoria()
        configuraFormulario(transacaoDelegate)
    }

    private fun configuraFormulario(transacaoDelegate: TransacaoDelegate) {
        AlertDialog.Builder(context)
            .setTitle(R.string.adiciona_receita)
            .setView(viewCriada)
            .setPositiveButton("Adicionar") { _, _ ->
                val valorEmTexto = viewCriada.form_transacao_valor.text.toString()
                val dataEmTexto = viewCriada.form_transacao_data.text.toString()
                val categoriaEmTexto = viewCriada.form_transacao_categoria.selectedItem.toString()
                val valor = converteCampoValor(valorEmTexto)
                val data = dataEmTexto.covertePraCalendar()
                val transacaoCriada = Transacao(tipo = Tipo.RECEITA, valor = valor, data = data, categoria = categoriaEmTexto)

                transacaoDelegate.delegate(transacaoCriada)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }


    private fun converteCampoValor(valorEmTexto: String) =
        try {
            BigDecimal(valorEmTexto)
        } catch (exception: NumberFormatException) {
            Toast.makeText(context, "Falha na conversão de valor", Toast.LENGTH_SHORT)
                .show()
            BigDecimal.ZERO
        }

    private fun configuraCampoCategoria() {
        val adapter = ArrayAdapter.createFromResource(
            context,
            R.array.categorias_de_receita,
            android.R.layout.simple_spinner_dropdown_item
        )
        viewCriada.form_transacao_categoria.adapter = adapter
    }

    private fun configuraCampoData() {
        val hoje = Calendar.getInstance()
        val ano = hoje.get(Calendar.YEAR)
        val mes = hoje.get(Calendar.MONTH)
        val dia = hoje.get(Calendar.DAY_OF_MONTH)

        viewCriada.form_transacao_data.setText(hoje.formataDataParaBrasileiro())
        viewCriada.form_transacao_data.setOnClickListener {
            DatePickerDialog(
                context, { _, ano, mes, dia ->
                    val dataSelecionada = Calendar.getInstance()
                    dataSelecionada.set(ano, mes, dia)
                    viewCriada.form_transacao_data.setText(dataSelecionada.formataDataParaBrasileiro())
                }, ano, mes, dia
            ).show()
        }
    }

    private fun criaLayout(): View {
        return LayoutInflater.from(context).inflate(R.layout.form_transacao, viewGroup, false)
    }

}