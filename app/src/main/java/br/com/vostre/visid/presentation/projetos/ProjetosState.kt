package br.com.vostre.visid.presentation.projetos

import br.com.vostre.visid.domain.model.Projeto

data class ProjetosState(
    val projetos : List<Projeto> = emptyList(),
    var projeto: Projeto = Projeto(),
    var isEdicao: Boolean = false,
    var formAberto: Boolean = false
)
