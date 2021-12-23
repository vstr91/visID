package br.com.vostre.visid.presentation.detalheProjeto

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.vostre.visid.domain.model.Cor
import br.com.vostre.visid.domain.model.Projeto
import br.com.vostre.visid.domain.usecase.CarregarCoresProjetoUseCase
import br.com.vostre.visid.domain.usecase.CarregarProjetoUseCase
import br.com.vostre.visid.domain.usecase.SalvarProjetoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetalheProjetoViewModel @Inject constructor(
    private val carregarProjetoUseCase: CarregarProjetoUseCase,
    private val carregarCoresProjetoUseCase: CarregarCoresProjetoUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(DetalheProjetoState())
    val state : State<DetalheProjetoState> = _state

    init {
        savedStateHandle.get<String>("id")?.let { id ->

            if(id.isNotEmpty()){
                viewModelScope.launch {
                    carregarProjetoUseCase(id)?.also { projeto ->
                        _state.value = state.value.copy(projeto = projeto)
                    }

                    carregarCoresProjetoUseCase(id).onEach { cores ->
                        _state.value = state.value.copy(cores = cores)
                    }.launchIn(viewModelScope)
                }

            }

        }

    }

    fun onEvent(event: DetalheProjetoEvent){
        when(event){
            is DetalheProjetoEvent.EditProjeto -> {
                _state.value = state.value.copy(formAberto = true,
                    projeto = event.projeto)
            }
            is DetalheProjetoEvent.DismissDialog -> {
                _state.value = state.value.copy(formAberto = false)
            }
            is DetalheProjetoEvent.AbrirDialogCor -> {
                _state.value = state.value.copy(formCorAberto = true, cor = event.cor)
            }
            is DetalheProjetoEvent.DismissDialogCor -> {
                _state.value = state.value.copy(formCorAberto = false, cor = Cor(event.projeto.id))
            }
        }
    }

}