package br.com.vostre.visid.presentation.formProjeto

import br.com.vostre.visid.domain.model.Projeto

sealed class FormProjetoEvent {
    data class EnteredTitle(val value: String) : FormProjetoEvent()
    data class EnteredDescription(val value: String) : FormProjetoEvent()
    data class SetProjeto(val projeto: Projeto): FormProjetoEvent()
    data class SaveProjeto(val projeto: Projeto): FormProjetoEvent()
    data class EditProjeto(val projeto: Projeto): FormProjetoEvent()
    object Dismiss: FormProjetoEvent()
}