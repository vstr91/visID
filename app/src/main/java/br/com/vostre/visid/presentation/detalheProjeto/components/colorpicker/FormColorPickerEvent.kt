package br.com.vostre.visid.presentation.detalheProjeto.components.colorpicker

import br.com.vostre.visid.domain.model.Cor
import br.com.vostre.visid.domain.model.Projeto

sealed class FormColorPickerEvent {
    data class SaveColor(val cor: Cor): FormColorPickerEvent()
}