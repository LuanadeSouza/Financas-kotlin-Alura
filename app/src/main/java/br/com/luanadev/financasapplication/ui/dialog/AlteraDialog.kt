package br.com.luanadev.financasapplication.ui.dialog

import android.content.Context
import android.view.ViewGroup
import br.com.luanadev.financasapplication.R
import br.com.luanadev.financasapplication.extension.formataDataParaBrasileiro
import br.com.luanadev.financasapplication.model.Tipo
import br.com.luanadev.financasapplication.model.Transacao

class AlteraDialog(viewGroup: ViewGroup, context: Context) :
    FormularioTransacaoDialog(context, viewGroup) {

    override fun tituloPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.string.altera_receita
        }
        return R.string.altera_despesa
    }
    override val tituloBotaoPositivo: String
        get() = "Alterar"

    fun chama(transacao: Transacao, delegate: (transacao: Transacao) -> Unit) {
        val tipo = transacao.tipo
        super.show(tipo, delegate)

        inicializaCampos(transacao, tipo)
    }

    private fun inicializaCampos(transacao: Transacao, tipo: Tipo) {
        campoValor.setText(transacao.valor.toString())
        campoData.setText(transacao.data.formataDataParaBrasileiro())
        val categoriasRetornadas = context.resources.getStringArray(categoriasPor(tipo))
        val posicaoCategoria = categoriasRetornadas.indexOf(transacao.categoria)
        campoCategoria.setSelection(posicaoCategoria, true)
    }


}