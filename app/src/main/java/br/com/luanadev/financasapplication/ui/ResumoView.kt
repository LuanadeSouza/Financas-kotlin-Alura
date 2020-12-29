package br.com.luanadev.financasapplication.ui

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import br.com.luanadev.financasapplication.R
import br.com.luanadev.financasapplication.extension.formataParaBrasileiro
import br.com.luanadev.financasapplication.model.Resumo
import br.com.luanadev.financasapplication.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(private val context: Context,  private val view: View, transacoes: List<Transacao>) {

    private val resumo: Resumo = Resumo(transacoes)
    fun adicionaReceita() {
       val totalReceita = resumo.receita()
        view.resumo_card_receita.setTextColor(ContextCompat.getColor(context, R.color.receita))
        view.resumo_card_receita.text = totalReceita.formataParaBrasileiro()
    }

    fun adicionaDespesa() {
      val totalDespesa = resumo.despesa()
        view.resumo_card_despesa.setTextColor(ContextCompat.getColor(context, R.color.despesa))
        view.resumo_card_despesa.text = totalDespesa.formataParaBrasileiro()
    }

    fun adicionaTotal() {
        val total = resumo.total()
        if(total >= BigDecimal.ZERO){
            view.resumo_card_total.setTextColor(ContextCompat.getColor(context, R.color.receita))
        }   else{
            view.resumo_card_total.setTextColor(ContextCompat.getColor(context, R.color.despesa))
        }
        view.resumo_card_total.text = total.formataParaBrasileiro()
    }
}