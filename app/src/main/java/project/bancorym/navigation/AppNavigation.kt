package project.bancorym.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import project.bancorym.views.LoginScreen
import project.bancorym.views.MainScreen
import project.bancorym.views.SplashScreen
import project.bancorym.models.InfoUser
import project.bancorym.views.RegisterScreen
import project.bancorym.viewsmodels.MyBiometricViewModel

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
            val context: Context = LocalContext.current
            val infoUser = InfoUser(context)
            MainScreen(infoUser)
        }
        composable(AppScreens.RegisterScreen.route) {
            val context: Context = LocalContext.current
            val infoUser = InfoUser(context)
            RegisterScreen(infoUser, navController)
        }
    }
}