package br.com.vostre.visid.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable

@Composable
fun VisIDTheme(darkTheme: Boolean  = isSystemInDarkTheme(), content: @Composable() () -> Unit){

    val darkColorPalette = darkColors(
        primary = primaryColor,
        primaryVariant = primaryColor,
        secondary = secondaryColor
    )

    val lightColorPalette = lightColors(
        primary = primaryColor,
        primaryVariant = primaryColor,
        secondary = secondaryColor
    )

    val colors = if(darkTheme){
        darkColorPalette
    } else{
        lightColorPalette
    }
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
    
}