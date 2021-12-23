package br.com.vostre.visid.presentation.projetos

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.vostre.visid.presentation.Screen
import br.com.vostre.visid.presentation.formProjeto.FormProjetoScreen
import br.com.vostre.visid.presentation.projetos.components.ProjetoListItem

@ExperimentalFoundationApi
@Composable
fun ProjetosScreen(
    navController: NavController,
    viewModel: ProjetosViewModel = hiltViewModel()
){
    val s_state = rememberScaffoldState()
    val state = viewModel.state.value

    Scaffold(
        scaffoldState = s_state,
        topBar = {
            br.com.vostre.visid.presentation.components.TopAppBar(
                isHome = true,
                navController = navController
            )
        },
        drawerContent = {

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(ProjetosEvent.NewProjeto)
                }
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Adicionar Projeto",
                    tint = Color.White
                )
            }
        },
        content = {

            if(state.formAberto) {
                FormProjetoScreen(
                    navController = navController,
                    projeto = state.projeto,
                    isEdicao = state.isEdicao,
                    onDismiss = {
                        viewModel.onEvent(ProjetosEvent.DismissDialog)
                    }
                )
            }

            Box(modifier = Modifier.fillMaxSize()){

                if(state.projetos.isNotEmpty()){
                    LazyColumn{
                        items(state.projetos){ projeto ->
                            ProjetoListItem(
                                projeto = projeto,
                                onItemClick = {
                                    navController.navigate(
                                        Screen.DetalheProjetoScreen.route+"?id=${projeto.id}")
                                },
                                onItemLongClick = {
                                    viewModel.onEvent(ProjetosEvent.EditProjeto(projeto))
                                }
                            )
                        }
                    }
                } else{
                    Text(
                        text = "Nenhum projeto cadastrado ainda...",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

            }

        }
    )
}