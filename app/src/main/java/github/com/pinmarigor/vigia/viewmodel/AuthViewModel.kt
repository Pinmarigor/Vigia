package github.com.pinmarigor.vigia.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
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

    private val listener = FirebaseAuth.AuthStateListener {
        authState = it.currentUser?.uid?.let(AuthState::Authenticated)
            ?: AuthState.Unauthenticated
    }

    init {
        FirebaseAuth.getInstance().addAuthStateListener(listener)
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch{
            userRepository.signIn(email, password)
        }
    }

    fun register(name: String, email: String, password: String, phone: String) {
        viewModelScope.launch{
            userRepository.register(name, email, password, phone)
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
