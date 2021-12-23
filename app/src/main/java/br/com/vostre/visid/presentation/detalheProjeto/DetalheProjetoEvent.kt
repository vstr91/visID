package br.com.vostre.visid.presentation.detalheProjeto

import br.com.vostre.visid.domain.model.Cor
import br.com.vostre.visid.domain.model.Projeto

sealed class DetalheProjetoEvent {
    data class EditProjeto(val projeto: Projeto): DetalheProjetoEvent()
    object DismissDialog: DetalheProjetoEvent()
    data class AbrirDialogCor(val cor: Cor): DetalheProjetoEvent()
    data class DismissDialogCor(val projeto: Projeto): DetalheProjetoEvent()
}