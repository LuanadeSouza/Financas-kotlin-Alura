package br.com.luanadev.financasapplication.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.luanadev.financasapplication.R
import br.com.luanadev.financasapplication.extension.formataParaBrasileiro
import br.com.luanadev.financasapplication.model.Tipo
import br.com.luanadev.financasapplication.model.Transacao
import br.com.luanadev.financasapplication.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.resumo_card.*
import java.math.BigDecimal

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        val transacoes: List<Transacao> = transacoesDeExemplo()
        adicionaReceitaNoResumo(transacoes)
        adicionaDespesaNoResumo(transacoes)
        adicionaTotalNoResumo(transacoes)
        configuraLista(transacoes)
    }


    private fun configuraLista(transacoes: List<Transacao>) {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }

    private fun transacoesDeExemplo() = listOf(
        Transacao(valor = BigDecimal(20.5), tipo = Tipo.DESPESA, categoria = "Almoço de Final de Semana"),
        Transacao(valor = BigDecimal(100.0), tipo = Tipo.RECEITA, categoria = "Economia"),
        Transacao(valor = BigDecimal(20.5), tipo = Tipo.DESPESA, categoria = "Almoço de Final de Semana"),
        Transacao(valor = BigDecimal(100.0), tipo = Tipo.RECEITA, categoria = "Economia"),
        Transacao(valor = BigDecimal(20.5), tipo = Tipo.DESPESA, categoria = "Almoço de Final de Semana"),
        Transacao(valor = BigDecimal(100.0), tipo = Tipo.RECEITA, categoria = "Economia")
    )

    private fun adicionaReceitaNoResumo(transacoes: List<Transacao>) {
        var totalReceita = BigDecimal.ZERO
        for (transacao in transacoes) {
            if (transacao.tipo == Tipo.RECEITA) {
                totalReceita = totalReceita.plus(transacao.valor)
            }
        }
        resumo_card_receita.text = totalReceita.formataParaBrasileiro()
    }

    private fun adicionaDespesaNoResumo(transacoes: List<Transacao>) {
        var totalDespesa = BigDecimal.ZERO
        for (transacao in transacoes) {
            if (transacao.tipo == Tipo.DESPESA) {
                totalDespesa = totalDespesa.plus(transacao.valor)
            }
        }
        resumo_card_despesa.text = totalDespesa.formataParaBrasileiro()
    }

    private fun adicionaTotalNoResumo(transacoes: List<Transacao>) {
        var total = BigDecimal.ZERO
        for (transacao in transacoes) {
            if (transacao.tipo == Tipo.RECEITA && transacao.tipo == Tipo.DESPESA) {
                total = total.plus(transacao.valor)
            }
        }
        resumo_card_total.text = total.formataParaBrasileiro()
    }


}
