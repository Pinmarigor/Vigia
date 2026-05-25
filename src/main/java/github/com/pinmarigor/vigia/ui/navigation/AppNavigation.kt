package github.com.pinmarigor.vigia.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import github.com.pinmarigor.vigia.ui.screens.ForgotPassword
import github.com.pinmarigor.vigia.ui.screens.ForgotPasswordCod
import github.com.pinmarigor.vigia.ui.screens.LoginScreen
import github.com.pinmarigor.vigia.ui.screens.RegisterScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val email : String

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            LoginScreen(navController)
        }

        composable("register") {
            RegisterScreen(navController)
        }

        composable("forgot_password") {
            ForgotPassword(navController)
        }

        composable("forgot_password_cod/{email}") {
            blackStackEntry ->

            val email = blackStackEntry.arguments?.getString("email")

            ForgotPasswordCod(navController, email)
        }
    }
}