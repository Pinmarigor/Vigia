package github.com.pinmarigor.vigia.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import github.com.pinmarigor.vigia.ui.screens.Home
import github.com.pinmarigor.vigia.ui.screens.login.ForgotPassword
import github.com.pinmarigor.vigia.ui.screens.login.ForgotPasswordCod
import github.com.pinmarigor.vigia.ui.screens.login.LoginScreen
import github.com.pinmarigor.vigia.ui.screens.login.MapScreen
import github.com.pinmarigor.vigia.ui.screens.login.NewPassword
import github.com.pinmarigor.vigia.ui.screens.login.RegisterScreen

@Composable
fun MainNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Route.LoginScreen
    ) {

        composable <Route.LoginScreen>{
            LoginScreen(navController)
        }

        composable <Route.RegisterScreen>{
            RegisterScreen(navController)
        }

        composable <Route.ForgotPassword>{
            ForgotPassword(navController)
        }

        composable <Route.ForgotPasswordCod>{ backStackEntry ->
            val route = backStackEntry.toRoute<Route.ForgotPasswordCod>()

            ForgotPasswordCod(
                navController = navController,
                email = route.email
            )
        }

        composable <Route.NewPassword>{
            NewPassword(navController)
        }

        composable<Route.Home> {
            Home(navController)
        }

        composable <Route.MapScreen>{
            MapScreen(navController)
        }

//        composable<Route.Community> {
//            CommunityScreen()
//        }
//
//        composable<Route.Alerts> {
//            AlertsScreen()
//        }
//
//        composable<Route.Contacts> {
//            ContactsScreen()
//        }
    }
}