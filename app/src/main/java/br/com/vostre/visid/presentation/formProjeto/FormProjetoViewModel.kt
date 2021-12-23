package br.com.vostre.visid.presentation.formProjeto

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.vostre.visid.domain.model.Projeto
import br.com.vostre.visid.domain.usecase.SalvarProjetoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.joda.time.DateTime
import javax.inject.Inject

@HiltViewModel
class FormProjetoViewModel @Inject constructor(
    private val salvarProjetoUseCase: SalvarProjetoUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _state = mutableStateOf(FormProjetoState())
    val state : State<FormProjetoState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _projetoState = mutableStateOf<Projeto>(Projeto())
    val projetoState : State<Projeto> = _projetoState

    private val _nomeState = mutableStateOf("")
    val nomeState : State<String> = _nomeState

    private val _descricaoState = mutableStateOf("")
    val descricaoState : State<String> = _descricaoState

    fun onEvent(event: FormProjetoEvent){
        when(event){
            is FormProjetoEvent.Dismiss -> {
                viewModelScope.launch {
                    _eventFlow.emit(UiEvent.CloseDialog)
                }
            }
            is FormProjetoEvent.SaveProjeto -> {

                viewModelScope.launch {
                    val projeto = event.projeto

//                    projeto.nome = nomeState.value
//                    projeto.descricao = descricaoState.value
                    projeto.dataCadastro = DateTime.now()
                    projeto.ultimaAlteracao = DateTime.now()

                    salvarProjetoUseCase.invoke(projeto)

                    _eventFlow.emit(UiEvent.SaveProjeto)
                }


            }
            is FormProjetoEvent.EditProjeto -> {

                viewModelScope.launch {
                    val projeto = event.projeto

                    projeto.ultimaAlteracao = DateTime.now()

                    salvarProjetoUseCase.invoke(projeto)

                    _eventFlow.emit(UiEvent.SaveProjeto)
                }


            }
        }
    }

    sealed class UiEvent{
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveProjeto: UiEvent()
        object CloseDialog: UiEvent()
        object Init: UiEvent()
    }

}