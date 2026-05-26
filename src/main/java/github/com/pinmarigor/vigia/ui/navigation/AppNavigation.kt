package github.com.pinmarigor.vigia.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import github.com.pinmarigor.vigia.ui.screens.login.ForgotPassword
import github.com.pinmarigor.vigia.ui.screens.login.ForgotPasswordCod
import github.com.pinmarigor.vigia.ui.screens.login.LoginScreen
import github.com.pinmarigor.vigia.ui.screens.login.NewPassword
import github.com.pinmarigor.vigia.ui.screens.login.RegisterScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

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

        composable("new_password") {
            NewPassword(navController)
        }
    }
}