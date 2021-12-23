package br.com.vostre.visid.presentation.formProjeto

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.vostre.visid.domain.model.Projeto
import br.com.vostre.visid.presentation.Screen
import br.com.vostre.visid.presentation.projetos.ProjetosEvent
import br.com.vostre.visid.presentation.projetos.ProjetosViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun FormProjetoScreen(
    navController: NavController,
    viewModel: FormProjetoViewModel = hiltViewModel(),
    projeto: Projeto = Projeto(),
    isEdicao: Boolean = false,
    onDismiss: () -> Unit
){
    val ctx = LocalContext.current

    var nome by remember{
        mutableStateOf(projeto.nome)
    }

    var descricao by remember{
        mutableStateOf(projeto.descricao)
    }

    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is FormProjetoViewModel.UiEvent.SaveProjeto -> {
                    Toast.makeText(ctx, "Projeto salvo!", Toast.LENGTH_SHORT).show()
                    onDismiss()

                    if(!isEdicao){
                        navController.navigate(
                            Screen.DetalheProjetoScreen.route+"?id=${projeto.id}"
                        )
                    }

                }
            }
        }
    }

    Dialog(
        onDismissRequest = {
            onDismiss()
        },

        content = {
            Card(
                backgroundColor = Color.White,
                elevation = 2.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ){
                    TextField(
                        value = nome,
                        onValueChange = { it ->
                            nome = it
                        },
                        label = {
                            Text(text = "Nome")
                        }
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    TextField(
                        value = descricao,
                        onValueChange = { it ->
                            descricao = it
                        },
                        label = {
                            Text(text = "Descrição")
                        }
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Button(
                        onClick = {
                            projeto.nome = nome
                            projeto.descricao = descricao

                            if(isEdicao){
                                viewModel.onEvent(FormProjetoEvent.EditProjeto(projeto))
                            } else{
                                viewModel.onEvent(FormProjetoEvent.SaveProjeto(projeto))
                            }

                        }
                    ) {
                        Text(text = "Salvar")
                    }
                }
            }
        }
    )

}