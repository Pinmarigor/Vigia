package github.com.pinmarigor.vigia.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import github.com.pinmarigor.vigia.data.repositories.UserRepository
import github.com.pinmarigor.vigia.viewmodel.AuthViewModel

class AuthViewModelFactory(
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}