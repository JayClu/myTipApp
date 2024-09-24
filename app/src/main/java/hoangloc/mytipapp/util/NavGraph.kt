package hoangloc.mytipapp.util

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hoangloc.mytipapp.screen.Routes
import hoangloc.mytipapp.screen.loginscreen
import hoangloc.mytipapp.screen.maintip

@Composable
fun  NavigationGraph(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.loginpage, builder = {
        composable(route = Routes.loginpage, content = { loginscreen(navController) })
        composable(Routes.maintip, content = { maintip() })
    })
}
