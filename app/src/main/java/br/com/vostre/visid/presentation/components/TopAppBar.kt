package br.com.vostre.visid.presentation.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun TopAppBar(
    isHome: Boolean,
    navController: NavController
){
    androidx.compose.material.TopAppBar(
        title = { Text("VisID") },
        navigationIcon = {
            if(isHome){
                IconButton(
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        Icons.Filled.Menu,
                        contentDescription = "Menu",
                        tint = Color.White
                    )
                }
            } else{
                IconButton(
                    onClick = { navController.navigateUp() }
                ) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }

        }
//        actions = {
//            IconButton(
//                onClick = { /*TODO*/ }
//            ) {
//                Icon(
//                    Icons.Filled.Search,
//                    contentDescription = "Search",
//                    tint = Color.White
//                )
//            }
//            IconButton(
//                onClick = { /*TODO*/ }
//            ) {
//                Icon(
//                    Icons.Filled.MoreVert,
//                    contentDescription = "Options",
//                    tint = Color.White
//                )
//            }
//        }
    )
}