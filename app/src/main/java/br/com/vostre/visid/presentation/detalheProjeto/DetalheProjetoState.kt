package br.com.vostre.visid.presentation.detalheProjeto

import br.com.vostre.visid.domain.model.Cor
import br.com.vostre.visid.domain.model.Projeto

data class DetalheProjetoState (
    val projeto: Projeto? = null,
    val cor: Cor = Cor(),
    var formAberto: Boolean = false,
    var formCorAberto: Boolean = false,
    val cores: List<Cor> = emptyList()
)