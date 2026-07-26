package github.com.pinmarigor.vigia.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

sealed interface AuthState {
    data object Loading : AuthState
    data object Unauthenticated : AuthState
    data class Authenticated(val uid: String) : AuthState
}

class AuthViewModel : ViewModel() {

    private val auth = Firebase.auth

    var authState by mutableStateOf<AuthState>(AuthState.Loading)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    private val listener = FirebaseAuth.AuthStateListener {
        authState = it.currentUser?.uid?.let(AuthState::Authenticated)
            ?: AuthState.Unauthenticated
    }

    init {
        auth.addAuthStateListener(listener)
    }

    fun signIn(email: String, password: String) {
        errorMessage = null
        auth.signInWithEmailAndPassword(email, password)
            .addOnFailureListener { exception ->
                errorMessage = exception.localizedMessage ?: "Não foi possível entrar."
            }
    }

    fun register(email: String, password: String) {
        errorMessage = null
        auth.createUserWithEmailAndPassword(email, password)
            .addOnFailureListener { exception ->
                errorMessage = exception.localizedMessage ?: "Não foi possível criar a conta."
            }
    }

    fun signOut() {
        auth.signOut()
    }

    fun consumeError() {
        errorMessage = null
    }

    override fun onCleared() {
        auth.removeAuthStateListener(listener)
        super.onCleared()
    }
}
