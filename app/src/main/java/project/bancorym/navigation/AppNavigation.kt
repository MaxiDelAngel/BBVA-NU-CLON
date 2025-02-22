package project.bancorym.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import project.bancorym.LoginScreen
import project.bancorym.MainScreen
import project.bancorym.SplashScreen

@Composable
fun AppNavigation() {
    //authenticate: (auth: (Boolean) -> Unit) -> Unit
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route
    ) {
        composable(AppScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(AppScreens.LoginScreen.route) {
            //LoginScreen(navController, authenticate)
            LoginScreen(navController)
        }
        composable(AppScreens.MainScreen.route) {
            MainScreen()
        }
    }
}