package br.com.luanadev.financasapplication.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.luanadev.financasapplication.R
import br.com.luanadev.financasapplication.model.Tipo
import br.com.luanadev.financasapplication.model.Transacao
import br.com.luanadev.financasapplication.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        val transacoes = listOf(
            Transacao(valor = BigDecimal(20.5), tipo = Tipo.DESPESA, categoria = "Almoço de Final de Semana"),
            Transacao(valor = BigDecimal(100.0), tipo = Tipo.RECEITA, categoria = "Economia")
        )
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }
}
