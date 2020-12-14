package br.com.luanadev.financasapplication.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.luanadev.financasapplication.R
import br.com.luanadev.financasapplication.extension.formataDataParaBrasileiro
import br.com.luanadev.financasapplication.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacoesAdapter(
    private val transacoes: List<Transacao>,
    private val context: Context
) : BaseAdapter() {

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val viewCriada =
            LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)
        val transacao = transacoes[position]
        viewCriada.transacao_valor.text = transacao.valor.toString()
        viewCriada.transacao_categoria.text = transacao.categoria
        viewCriada.transacao_data.text = transacao.data.formataDataParaBrasileiro()

        return viewCriada
    }

    override fun getItem(position: Int): Transacao {
        return transacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transacoes.size
    }
}