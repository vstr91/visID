package br.com.vostre.visid.presentation.projetos

import br.com.vostre.visid.domain.model.Projeto

sealed class ProjetosEvent {
    object NewProjeto: ProjetosEvent()
    data class EditProjeto(val projeto: Projeto): ProjetosEvent()
    object DismissDialog: ProjetosEvent()
}