package github.com.pinmarigor.vigia.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import github.com.pinmarigor.vigia.data.repositories.PostRepository
import github.com.pinmarigor.vigia.network.repository.NominatimRepository
import github.com.pinmarigor.vigia.viewmodel.PostViewModel

class PostViewModelFactory(
    private val nominatimRepository: NominatimRepository,
    private val postRepository: PostRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
            return PostViewModel(nominatimRepository, postRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
