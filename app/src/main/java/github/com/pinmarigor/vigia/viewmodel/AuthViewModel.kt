package github.com.pinmarigor.vigia.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import github.com.pinmarigor.vigia.data.model.User
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

    var currentUser by mutableStateOf<User?>(null)
        private set

    private val listener = FirebaseAuth.AuthStateListener { auth ->
        authState = auth.currentUser?.uid?.let(AuthState::Authenticated)
            ?: AuthState.Unauthenticated

        if (authState is AuthState.Unauthenticated) {
            currentUser = null
        }

        if (authState is AuthState.Authenticated) {
            viewModelScope.launch {
                try {
                    currentUser = userRepository.getCurrentUser()
                    userRepository.syncVerification()
                } catch (e: Exception) {
                    Log.e("AUTH","erro ao sincronizar dados do usuário: ${e.message}")
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
                currentUser = userRepository.getCurrentUser()
            } catch (e: FirebaseAuthInvalidCredentialsException) {
                errorMessage = "E-mail ou senha inválidos."
            } catch (e: FirebaseAuthWeakPasswordException) {
                errorMessage = "A senha é muito fraca."
            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "Erro ao fazer login."
            }
        }
    }

    fun register(name: String, email: String, password: String, phone: String) {
        viewModelScope.launch{
            try {
                userRepository.register(name, email, password, phone)
                currentUser = userRepository.getCurrentUser()
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
        currentUser = null
    }

    fun deleteAccount() {
        val uid = (authState as? AuthState.Authenticated)?.uid ?: return
        viewModelScope.launch {
            try {
                userRepository.delete(uid)
            } catch (e: FirebaseAuthRecentLoginRequiredException) {
                errorMessage = "Por segurança, faça login novamente antes de excluir sua conta."
            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "Erro ao excluir conta."
            }
        }
    }

    fun consumeError() {
        errorMessage = null
    }

    override fun onCleared() {
        super.onCleared()
        FirebaseAuth.getInstance().removeAuthStateListener(listener)
    }
}
