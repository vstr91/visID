package br.com.vostre.visid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.vostre.visid.presentation.Screen
import br.com.vostre.visid.presentation.detalheProjeto.DetalheProjetoScreen
import br.com.vostre.visid.presentation.projetos.ProjetosScreen
import br.com.vostre.visid.presentation.ui.theme.VisIDTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @ExperimentalGraphicsApi
    @ExperimentalAnimationApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VisIDTheme() {
                val navController = rememberAnimatedNavController()
                AnimatedNavHost(
                    navController = navController,
                    startDestination = Screen.ProjetosScreen.route
                ){
                    composable(
                        route = Screen.ProjetosScreen.route,
                        enterTransition = {
                            fadeIn(animationSpec = tween(500))
                        }
                    ){
                        ProjetosScreen(navController)
                    }
                    composable(
                        route = Screen.DetalheProjetoScreen.route+"?id={id}",
                        exitTransition = {
                            fadeOut(animationSpec = tween(500))
                        },
                        arguments = listOf(
                            navArgument(
                                name = "id"
                            ){
                                type = NavType.StringType
                                defaultValue = ""
                            }
                        )
                    ){
                        DetalheProjetoScreen(navController)
                    }
                }
            }
        }
    }
}