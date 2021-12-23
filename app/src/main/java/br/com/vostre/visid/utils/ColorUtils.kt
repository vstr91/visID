package br.com.vostre.visid.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

fun Color.argbToHex(alpha: Boolean = false) : String{

    var hex = "%X".format(this.toArgb())

    if(alpha){
        return hex
    } else{
        return hex.substring(2, hex.length)
    }

}

fun Color.Companion.fromHex(value: String): Color{
    return Color(android.graphics.Color.parseColor("#${value}"))
}