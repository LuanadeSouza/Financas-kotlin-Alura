package br.com.luanadev.financasapplication.ui.activity

import android.os.Bundle
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
    private val viewDaActivity by lazy { window.decorView }
    private val viewGroupDaActivity by lazy { viewDaActivity as ViewGroup }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        configuraResumo()
        configuraLista()
        adicionaReceita(Tipo.RECEITA)
        adicionaDespesa(Tipo.DESPESA)
    }

    private fun adicionaDespesa(tipo: Tipo) {
        lista_transacoes_adiciona_despesa.setOnClickListener {
            AdicionaDialog(viewGroupDaActivity, this)
                .chama(tipo) {
                    adiciona(it)
                    lista_transacoes_adiciona_menu.close(true)
                }
        }
    }

    private fun adicionaReceita(tipo: Tipo) {
        lista_transacoes_adiciona_receita.setOnClickListener {
            AdicionaDialog(viewGroupDaActivity, this)
                .chama(tipo) {
                    adiciona(it)
                    lista_transacoes_adiciona_menu.close(true)
                }
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
        AlteraDialog(viewDaActivity as ViewGroup, this).chama(
            transacao, object : TransacaoDelegate {
                override fun delegate(transacao: Transacao) {
                    transacoes[position] = transacao
                    atualizaTransacoes()
                }
            })
    }
}
