package br.com.luanadev.financasapplication.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.luanadev.financasapplication.R
import br.com.luanadev.financasapplication.model.Tipo
import br.com.luanadev.financasapplication.model.Transacao
import br.com.luanadev.financasapplication.ui.ResumoView
import br.com.luanadev.financasapplication.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal

class   ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        val transacoes: List<Transacao> = transacoesDeExemplo()
        configuraResumo(transacoes)
        configuraLista(transacoes)
    }

    private fun configuraResumo(transacoes: List<Transacao>) {
        val view = window.decorView
        val resumoView = ResumoView(this, view, transacoes)
        resumoView.atualiza()
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
}
