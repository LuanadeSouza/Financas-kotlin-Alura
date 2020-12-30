package br.com.luanadev.financasapplication.ui.activity

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import br.com.luanadev.financasapplication.R
import br.com.luanadev.financasapplication.delegate.TransacaoDelegate
import br.com.luanadev.financasapplication.model.Transacao
import br.com.luanadev.financasapplication.ui.ResumoView
import br.com.luanadev.financasapplication.ui.adapter.ListaTransacoesAdapter
import br.com.luanadev.financasapplication.ui.dialog.Dialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        configuraResumo()
        configuraLista()

        lista_transacoes_adiciona_receita.setOnClickListener {
            Dialog(window.decorView as ViewGroup, this)
                .configuraDiolog(object : TransacaoDelegate {
                    override fun delegate(transacao: Transacao) {
                        atualizaTransacoes(transacao)
                        lista_transacoes_adiciona_menu.close(true)
                    }
                })
        }
    }

    private fun atualizaTransacoes(transacao: Transacao) {
        transacoes.add(transacao)
        configuraLista()
        configuraResumo()
    }

    private fun configuraResumo() {
        val view = window.decorView
        val resumoView = ResumoView(this, view, transacoes)
        resumoView.atualiza()
    }

    private fun configuraLista() {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }
}
