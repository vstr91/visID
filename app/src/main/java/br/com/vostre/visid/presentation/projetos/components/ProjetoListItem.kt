package br.com.vostre.visid.presentation.projetos.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.vostre.visid.domain.model.Projeto
import org.joda.time.format.DateTimeFormat

@ExperimentalFoundationApi
@Composable
fun ProjetoListItem(projeto: Projeto, onItemClick: (Projeto) -> Unit, onItemLongClick: (Projeto) -> Unit){
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .combinedClickable(
                onClick = { onItemClick(projeto) },
                onLongClick = { onItemLongClick(projeto) }
            )
    ) {
        Text(text = projeto.nome)
        Text(text = "Data cadastro: ${DateTimeFormat.forPattern("dd/MM/yyyy HH:mm")
            .print(projeto.dataCadastro)}")
        Text(text = "Última alteração: ${DateTimeFormat.forPattern("dd/MM/yyyy HH:mm")
            .print(projeto.ultimaAlteracao)}")
    }
}