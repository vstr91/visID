package br.com.vostre.visid.presentation.formProjeto

import br.com.vostre.visid.domain.model.Projeto

data class FormProjetoState(
    var projeto: Projeto = Projeto(),
    val isEdicao: Boolean  = false
)