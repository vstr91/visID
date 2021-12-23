package br.com.vostre.visid.presentation.detalheProjeto.components.colorpicker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.vostre.visid.domain.usecase.SalvarCorUseCase
import br.com.vostre.visid.presentation.formProjeto.FormProjetoViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormColorPickerViewModel @Inject constructor(
    private val salvarCorUseCase: SalvarCorUseCase
): ViewModel() {

    private val _eventFlow = MutableSharedFlow<FormColorPickerViewModel.UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: FormColorPickerEvent){
        when(event){
            is FormColorPickerEvent.SaveColor -> {
                viewModelScope.launch {
                    salvarCorUseCase.invoke(event.cor)
                    _eventFlow.emit(UiEvent.SaveCor)
                }

            }
        }
    }

    sealed class UiEvent{
        object SaveCor: UiEvent()
    }

}