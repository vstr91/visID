package br.com.vostre.visid.presentation.detalheProjeto

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.vostre.visid.presentation.detalheProjeto.components.ColorListItem
import br.com.vostre.visid.presentation.detalheProjeto.components.FormColorPicker
import br.com.vostre.visid.presentation.formProjeto.FormProjetoScreen
import br.com.vostre.visid.presentation.projetos.ProjetosEvent

@ExperimentalFoundationApi
@ExperimentalGraphicsApi
@Composable
fun DetalheProjetoScreen(
    navController: NavController,
    viewModel: DetalheProjetoViewModel = hiltViewModel()
){
    val state = viewModel.state.value

    state.projeto?.let { projeto ->
        val s_state = rememberScaffoldState()

        Scaffold(
            scaffoldState = s_state,
            topBar = {
                 br.com.vostre.visid.presentation.components.TopAppBar(
                     isHome = false,
                     navController = navController
                 )
            },
            drawerContent = {

            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        viewModel.onEvent(DetalheProjetoEvent.EditProjeto(state.projeto))
                    }
                ) {
                    Icon(
                        Icons.Filled.Edit,
                        contentDescription = "Editar Projeto",
                        tint = Color.White
                    )
                }
            },
            content = {

                if(state.formAberto) {
                    FormProjetoScreen(
                        navController = navController,
                        projeto = state.projeto,
                        isEdicao = true,
                        onDismiss = {
                            viewModel.onEvent(DetalheProjetoEvent.DismissDialog)
                        }
                    )
                }

                if(state.formCorAberto) {
                    FormColorPicker(
                        onDismiss = {
                            viewModel.onEvent(DetalheProjetoEvent.DismissDialogCor(projeto))
                        },
                        projeto = projeto.id,
                        cor = state.cor
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ){
                    Text(
                        text = "Projeto ${projeto.nome}",
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )
                    Text(
                        text = projeto.descricao,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Cores do Projeto")
                        IconButton(onClick = {
                            viewModel.onEvent(DetalheProjetoEvent.AbrirDialogCor(state.cor))
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Add Color"
                            )
                        }
                    }


                    if(state.cores.isNotEmpty()){
                        LazyColumn(
                            modifier = Modifier
                                .padding(8.dp)
                        ) {
                            items(state.cores){ cor ->
                                ColorListItem(
                                    cor = cor,
                                    onItemLongClick = { cor ->
                                        viewModel.onEvent(DetalheProjetoEvent.AbrirDialogCor(cor))
                                    }
                                )
                            }
                        }
                    }

                }
            }
        )
    }
}