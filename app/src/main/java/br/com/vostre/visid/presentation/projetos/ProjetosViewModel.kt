package br.com.vostre.visid.presentation.projetos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.vostre.visid.domain.model.Projeto
import br.com.vostre.visid.domain.usecase.ListarProjetosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProjetosViewModel @Inject constructor(
    private val listarProjetosUseCase: ListarProjetosUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ProjetosState())
    val state : State<ProjetosState> = _state

    init {
        listarProjetos()
    }

    fun onEvent(event: ProjetosEvent){
        when(event){
            is ProjetosEvent.NewProjeto -> {
                _state.value = state.value.copy(formAberto = true, isEdicao = false,
                    projeto = Projeto())
            }
            is ProjetosEvent.EditProjeto -> {
                _state.value = state.value.copy(formAberto = true, isEdicao = true,
                    projeto = event.projeto)
            }
            is ProjetosEvent.DismissDialog -> {
                _state.value = state.value.copy(formAberto = false)
            }
        }
    }

    private fun listarProjetos(){
        listarProjetosUseCase().onEach { projetos ->
            _state.value = state.value.copy(projetos = projetos)
        }.launchIn(viewModelScope)
    }

}