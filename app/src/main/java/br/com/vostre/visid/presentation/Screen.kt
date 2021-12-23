package br.com.vostre.visid.presentation

sealed class Screen(val route: String){
    object ProjetosScreen : Screen("projetos_screen")
    object DetalheProjetoScreen : Screen("detalhe_projeto_screen")
}