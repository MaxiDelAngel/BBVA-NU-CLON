package project.bancorym.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import project.bancorym.LoginScreen
import project.bancorym.MainScreen
import project.bancorym.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val biometricViewModel: MyBiometricViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route
    ) {
        composable(AppScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(AppScreens.LoginScreen.route) {
            LoginScreen(navController, biometricViewModel)
        }
        composable(AppScreens.MainScreen.route) {
            MainScreen()
        }
    }
}