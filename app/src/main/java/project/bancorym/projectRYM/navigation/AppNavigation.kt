package project.bancorym.projectRYM.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import project.bancorym.projectRYM.ui.views.LoginScreen
import project.bancorym.projectRYM.ui.views.MainScreen
import project.bancorym.projectRYM.ui.views.SplashScreen
import project.bancorym.projectRYM.ui.models.InfoUser
import project.bancorym.projectRYM.ui.views.RegisterScreen
import project.bancorym.projectRYM.ui.viewsmodels.MyBiometricViewModel

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