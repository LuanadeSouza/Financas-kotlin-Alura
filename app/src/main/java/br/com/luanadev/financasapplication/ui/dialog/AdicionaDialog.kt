package br.com.luanadev.financasapplication.ui.dialog

import android.content.Context
import android.view.ViewGroup
import br.com.luanadev.financasapplication.R
import br.com.luanadev.financasapplication.model.Tipo

class AdicionaDialog(viewGroup: ViewGroup, context: Context) : FormularioTransacaoDialog(context, viewGroup) {
    override fun tituloPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.string.adiciona_receita
        }
        return R.string.adiciona_despesa
    }

    override val tituloBotaoPositivo: String
        get() = "Adicionar"
}