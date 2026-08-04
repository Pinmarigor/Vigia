package github.com.pinmarigor.vigia.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Home : Route

    @Serializable
    data object LoginScreen : Route

    @Serializable
    data object RegisterScreen: Route

    @Serializable
    data object ForgotPassword : Route

    @Serializable
    data class ForgotPasswordCod(
        val email: String
    ) : Route

    @Serializable
    data object NewPassword : Route

    @Serializable
    data object MapScreen : Route

    @Serializable
    data object Community : Route

    @Serializable
    data object Warnings : Route

    @Serializable
    data object Contacts : Route

    @Serializable
    data object Configs : Route

    @Serializable
    data class Comments(
        val count: Int
    ): Route

    @Serializable
    data object SosScreen : Route

    @Serializable
    data object CreatePost : Route
}
