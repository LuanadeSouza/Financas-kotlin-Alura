package br.com.luanadev.financasapplication.ui.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import br.com.luanadev.financasapplication.R
import br.com.luanadev.financasapplication.delegate.TransacaoDelegate
import br.com.luanadev.financasapplication.model.Tipo
import br.com.luanadev.financasapplication.model.Transacao
import br.com.luanadev.financasapplication.ui.ResumoView
import br.com.luanadev.financasapplication.ui.adapter.ListaTransacoesAdapter
import br.com.luanadev.financasapplication.ui.dialog.AdicionaDialog
import br.com.luanadev.financasapplication.ui.dialog.AlteraDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()
    private lateinit var viewDaActivity: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        viewDaActivity = window.decorView
        configuraResumo()
        configuraLista()
        adicionaReceita(Tipo.RECEITA)
        adicionaDespesa(Tipo.DESPESA)
    }

    private fun adicionaDespesa(tipo: Tipo) {
        lista_transacoes_adiciona_despesa.setOnClickListener {
            AdicionaDialog(viewDaActivity as ViewGroup, this)
                .show(tipo, object : TransacaoDelegate {
                    override fun delegate(transacao: Transacao) {
                        atualizaTransacoes()
                        lista_transacoes_adiciona_menu.close(true)
                    }
                })
        }
    }

    private fun adicionaReceita(tipo: Tipo) {
        lista_transacoes_adiciona_receita.setOnClickListener {
            AdicionaDialog(viewDaActivity as ViewGroup, this)
                .show(
                    tipo,
                    object : TransacaoDelegate {
                        override fun delegate(transacao: Transacao) {
                            adiciona(transacao)
                            lista_transacoes_adiciona_menu.close(true)
                        }
                    },
                )
        }
    }

    private fun adiciona(transacao: Transacao) {
        transacoes.add(transacao)
        atualizaTransacoes()
    }

    private fun atualizaTransacoes() {
        configuraLista()
        configuraResumo()
    }

    private fun configuraResumo() {
        val resumoView = ResumoView(this, viewDaActivity, transacoes)
        resumoView.atualiza()
    }

    private fun configuraLista() {
        val listaTransacoesAdapter = ListaTransacoesAdapter(transacoes, this)
        with(lista_transacoes_listview) {
            adapter = listaTransacoesAdapter
            setOnItemClickListener { _, _, position, _ ->
                val transacao = transacoes[position]
                altera(transacao, position)
            }
        }
    }

    private fun altera(
        transacao: Transacao,
        position: Int
    ) {
        AlteraDialog(viewDaActivity as ViewGroup, this).show(
            transacao, object : TransacaoDelegate {
                override fun delegate(transacao: Transacao) {
                    transacoes[position] = transacao
                    atualizaTransacoes()
                }
            })
    }
}
