package github.com.pinmarigor.vigia.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import github.com.pinmarigor.vigia.data.repositories.UserRepository
import kotlinx.coroutines.launch

sealed interface AuthState {
    data object Loading : AuthState
    data object Unauthenticated : AuthState
    data class Authenticated(val uid: String) : AuthState
}

class AuthViewModel(
    private val userRepository: UserRepository
) : ViewModel() {


    var authState by mutableStateOf<AuthState>(AuthState.Loading)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    private val listener = FirebaseAuth.AuthStateListener { auth ->
        Log.d("AUTH", "Listener executado")

        authState = auth.currentUser?.uid?.let(AuthState::Authenticated)
            ?: AuthState.Unauthenticated

        Log.d("AUTH", authState.toString())

        if (authState is AuthState.Authenticated) {
            viewModelScope.launch {
                try {
                    userRepository.syncVerification()
                } catch (e: Exception) {
                    Log.e("AUTH","erro ao sincronizar verificação: ${e.message}")
                }
            }
        }
    }

    init {
        FirebaseAuth.getInstance().addAuthStateListener(listener)
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch{
            try {
                userRepository.signIn(email, password)
            } catch (e: FirebaseAuthUserCollisionException) {
                errorMessage = "Este e-mail já está cadastrado."
            } catch (e: FirebaseAuthInvalidCredentialsException) {
                errorMessage = "E-mail inválido."
            } catch (e: FirebaseAuthWeakPasswordException) {
                errorMessage = "A senha é muito fraca."
            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "Erro ao cadastrar usuário."
            }
        }
    }

    fun register(name: String, email: String, password: String, phone: String) {
        viewModelScope.launch{
            try {
                userRepository.register(name, email, password, phone)
            } catch (e: FirebaseAuthUserCollisionException) {
                errorMessage = "Este e-mail já está cadastrado."
            } catch (e: FirebaseAuthInvalidCredentialsException) {
                errorMessage = "E-mail inválido."
            } catch (e: FirebaseAuthWeakPasswordException) {
                errorMessage = "A senha é muito fraca."
            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "Erro ao cadastrar usuário."
            }
        }
    }

    fun signOut() {
        userRepository.signOut()
    }

    fun consumeError() {
        errorMessage = null
    }

    override fun onCleared() {
        super.onCleared()
        FirebaseAuth.getInstance().removeAuthStateListener(listener)
    }
}
