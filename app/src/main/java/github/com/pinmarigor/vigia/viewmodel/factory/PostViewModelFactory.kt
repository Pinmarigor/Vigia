package github.com.pinmarigor.vigia.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import github.com.pinmarigor.vigia.network.repository.NominatimRepository
import github.com.pinmarigor.vigia.viewmodel.PostViewModel

class PostViewModelFactory(
    private val repository: NominatimRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
            return PostViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}