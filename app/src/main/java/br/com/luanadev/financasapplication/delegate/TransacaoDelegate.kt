package br.com.luanadev.financasapplication.delegate

import br.com.luanadev.financasapplication.model.Transacao

interface TransacaoDelegate {

    fun delegate(transacao: Transacao)
}