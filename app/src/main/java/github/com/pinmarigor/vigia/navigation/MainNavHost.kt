package github.com.pinmarigor.vigia.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import github.com.pinmarigor.vigia.ui.screens.Comments
import github.com.pinmarigor.vigia.ui.screens.Community
import github.com.pinmarigor.vigia.ui.screens.Configs
import github.com.pinmarigor.vigia.ui.screens.Contacts
import github.com.pinmarigor.vigia.ui.screens.Home
import github.com.pinmarigor.vigia.ui.screens.MapScreen
import github.com.pinmarigor.vigia.ui.screens.SosScreen
import github.com.pinmarigor.vigia.ui.screens.Warnings
import github.com.pinmarigor.vigia.ui.screens.login.ForgotPassword
import github.com.pinmarigor.vigia.ui.screens.login.ForgotPasswordCod
import github.com.pinmarigor.vigia.ui.screens.login.LoginScreen
import github.com.pinmarigor.vigia.ui.screens.login.NewPassword
import github.com.pinmarigor.vigia.ui.screens.login.RegisterScreen
import github.com.pinmarigor.vigia.ui.screens.CreatePostScreen
import github.com.pinmarigor.vigia.viewmodel.AuthState
import github.com.pinmarigor.vigia.viewmodel.AuthViewModel
import github.com.pinmarigor.vigia.viewmodel.PostViewModel

@Composable
fun MainNavHost(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    postViewModel: PostViewModel
) {
    val authState = authViewModel.authState

    if (authState is AuthState.Loading) {
        Box(modifier = Modifier.fillMaxSize())
        return
    }

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> navController.navigate(Route.Home) {
                popUpTo(navController.graph.findStartDestination().id) { inclusive = true }
                launchSingleTop = true
            }

            AuthState.Unauthenticated -> navController.navigate(Route.LoginScreen) {
                popUpTo(navController.graph.findStartDestination().id) { inclusive = true }
                launchSingleTop = true
            }

            AuthState.Loading -> Unit
        }
    }

    NavHost(
        navController = navController,
        startDestination =
            if (authState is AuthState.Authenticated)
                Route.Home
            else
                Route.LoginScreen
    ) {

        composable<Route.LoginScreen> {
            LoginScreen(
                navController = navController,
                onSignIn = authViewModel::signIn,
                errorMessage = authViewModel.errorMessage,
                onErrorConsumed = authViewModel::consumeError
            )
        }

        composable<Route.RegisterScreen> {
            RegisterScreen(
                navController = navController,
                onRegister = authViewModel::register,
                errorMessage = authViewModel.errorMessage,
                onErrorConsumed = authViewModel::consumeError
            )
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

        composable<Route.Community> {
            Community(
                navController = navController,
                postViewModel = postViewModel,
                authViewModel = authViewModel
            )
        }

        composable<Route.Warnings> {
            Warnings(navController)
        }

        composable<Route.Contacts> {
            Contacts(navController)
        }

        composable<Route.Configs> {
            Configs(
                navController = navController,
                authViewModel = authViewModel,
                onSignOut = authViewModel::signOut,
                onDelete =  authViewModel::deleteAccount,
            )
        }

        composable<Route.Comments> { backStackEntry ->
            val route = backStackEntry.toRoute<Route.Comments>()

            Comments(
                navController = navController,
                postId = route.postId,
                count = route.count,
                postViewModel = postViewModel,
                authViewModel = authViewModel
            )
        }

        composable<Route.SosScreen> {
            SosScreen(navController)
        }

        composable<Route.CreatePost> {
            CreatePostScreen(
                navController = navController,
                postViewModel = postViewModel,
                authViewModel = authViewModel
            )
        }
    }
}
