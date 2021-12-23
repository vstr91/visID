package br.com.vostre.visid.presentation.detalheProjeto.components

import android.widget.Toast
import androidx.compose.animation.VectorConverter
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.core.view.size
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.vostre.visid.domain.model.Cor
import br.com.vostre.visid.presentation.Screen
import br.com.vostre.visid.presentation.detalheProjeto.components.colorpicker.FormColorPickerEvent
import br.com.vostre.visid.presentation.detalheProjeto.components.colorpicker.FormColorPickerViewModel
import br.com.vostre.visid.presentation.formProjeto.FormProjetoEvent
import br.com.vostre.visid.presentation.formProjeto.FormProjetoViewModel
import br.com.vostre.visid.utils.argbToHex
import br.com.vostre.visid.utils.fromHex
import com.godaddy.android.colorpicker.ClassicColorPicker
import com.godaddy.android.colorpicker.HsvColor
import kotlinx.coroutines.flow.collectLatest

@ExperimentalGraphicsApi
@Composable
fun FormColorPicker(
    onDismiss: () -> Unit,
    projeto: String,
    cor: Cor = Cor(projeto = projeto),
    viewModel: FormColorPickerViewModel = hiltViewModel()
){

    val corEscolhida = remember {
        mutableStateOf(Color.fromHex(cor.cor))
    }

    val hex = remember {
        mutableStateOf(cor.cor)
    }

    val textFieldValue = remember {
        mutableStateOf(cor.cor)
    }

    val nome = remember {
        mutableStateOf(cor.nome)
    }

    val ctx = LocalContext.current

    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is FormColorPickerViewModel.UiEvent.SaveCor -> {
                    Toast.makeText(ctx, "Cor salva!", Toast.LENGTH_SHORT).show()
                    onDismiss()
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
                        value = nome.value,
                        onValueChange = {
                            nome.value = it
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    ClassicColorPicker(
                        color = Color.fromHex(hex.value),
                        showAlphaBar = false,
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                            .padding(8.dp),
                        onColorChanged = { color ->
                            corEscolhida.value = color.toColor()
                            hex.value = corEscolhida.value.argbToHex()
                            textFieldValue.value = hex.value
                        }
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        TextField(
                            value = textFieldValue.value,
                            onValueChange = {
                                textFieldValue.value = it

                                if (it.length == 6 && it
                                        .matches("^[0-9A-Fa-f]{6}$".toRegex())){
                                    hex.value = it
                                    corEscolhida.value = Color.fromHex(hex.value)
                                }

                            },
                            modifier = Modifier
                                .padding(8.dp)
                                .width(100.dp)
                                .onFocusChanged {

                                    val stringDigitada = textFieldValue.value

                                    if (stringDigitada.length == 6 && stringDigitada
                                            .matches("^[0-9A-Fa-f]{6}$".toRegex())
                                    ) {
                                        hex.value = stringDigitada
                                    } else {
                                        Toast
                                            .makeText(ctx, "Valor inválido", Toast.LENGTH_SHORT)
                                            .show()
                                        textFieldValue.value = hex.value
                                    }

                                }
                        )
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .size(50.dp)
                                .background(
                                    color = Color
                                        .fromHex(hex.value)
                                )
                        )
                    }

                    Spacer(modifier = Modifier.size(8.dp))
                    Row(modifier = Modifier
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Button(onClick = {

                            if(nome.value.isNotEmpty()){
                                hex.value = corEscolhida.value.argbToHex()

                                cor.cor = hex.value
                                cor.nome = nome.value
                                cor.projeto = projeto

                                viewModel.onEvent(FormColorPickerEvent.SaveColor(cor))
                            } else{
                                Toast.makeText(
                                    ctx,
                                    "Por favor, preencha todos os dados.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        }) {
                            Text("Salvar")
                        }
                        OutlinedButton(
                            onClick = {
                                onDismiss()
                            },

                        ) {
                            Text("Fechar")
                        }
                    }

                }
            }
        }
    )
}