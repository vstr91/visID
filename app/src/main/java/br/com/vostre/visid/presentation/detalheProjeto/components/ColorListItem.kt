package br.com.vostre.visid.presentation.detalheProjeto.components

import androidx.annotation.ColorInt
import androidx.annotation.Size
import androidx.compose.animation.VectorConverter
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.vostre.visid.domain.model.Cor
import br.com.vostre.visid.utils.fromHex

@ExperimentalFoundationApi
@Composable
fun ColorListItem(
    cor: Cor,
    onItemLongClick: (Cor) -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable (
                onClick = {  },
                onLongClick = { onItemLongClick(cor) }
            )
    ){
        Box(
            modifier = Modifier
                .background(color = Color.fromHex(cor.cor))
                .size(36.dp)
        )
        Text(text = "#${cor.cor}",
            color = Color.Gray,
            modifier = Modifier
                .padding(8.dp)
                .align(CenterVertically)
        )
        Text(cor.nome,
            modifier = Modifier
                .padding(8.dp)
                .align(CenterVertically)
        )
    }
}