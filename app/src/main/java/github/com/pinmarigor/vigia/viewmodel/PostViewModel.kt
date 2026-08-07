package github.com.pinmarigor.vigia.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.com.pinmarigor.vigia.data.model.Post
import github.com.pinmarigor.vigia.data.model.PostType
import github.com.pinmarigor.vigia.data.repositories.PostRepository
import github.com.pinmarigor.vigia.network.model.LocationInfo
import github.com.pinmarigor.vigia.network.model.SearchLocation
import github.com.pinmarigor.vigia.network.repository.NominatimRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class PostViewModel(
    private val nominatimRepository: NominatimRepository,
    private val postRepository: PostRepository
) : ViewModel() {
    private val _location =
        MutableStateFlow<LocationInfo?>(null)
    val locationState = _location.asStateFlow()

    private val _searchResults = MutableStateFlow<List<SearchLocation>>(emptyList())
    val searchState = _searchResults.asStateFlow()

    private val _selectedLocation = MutableStateFlow<SearchLocation?>(null)
    val selectedLocationState = _selectedLocation.asStateFlow()

    private val likeJobs = mutableMapOf<String, Job>()
    private var searchJob: Job? = null

    val postsState: StateFlow<List<Post>> = postRepository.getPostsFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _selectedPost = MutableStateFlow<Post?>(null)
    val selectedPostState = _selectedPost.asStateFlow()

    fun getPostById(postId: String) {
        viewModelScope.launch {
            _selectedPost.value = postRepository.getById(postId)
        }
    }

    fun reverseGeocode(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                val location = nominatimRepository.reverseGeocode(lat, lon)
                _location.value = location
            } catch (e: Exception) {
                Log.e("SEARCH","Localização não encontrada", e)
            }
        }
    }

    fun searchLocation(query: String) {
        val text = query.trim()
        
        searchJob?.cancel()
        
        if (text.length < 3) {
            _searchResults.value = emptyList()
            return
        }

        searchJob = viewModelScope.launch {
            delay(500)
            try {
                val locations = nominatimRepository.searchLocation(text)
                _searchResults.value = locations
            } catch (e: Exception) {
                Log.e("SEARCH", "Erro ao buscar localidade: $text", e)
                _searchResults.value = emptyList()
            }
        }
    }

    fun selectLocation(location: SearchLocation?) {
        _selectedLocation.value = location
    }

    fun clearSearch() {
        _searchResults.value = emptyList()
    }

    fun publishPost(
        description: String,
        type: PostType,
        latitude: Double?,
        longitude: Double?,
        locationName: String,
        authorId: String
    ) {
        viewModelScope.launch {
            try {
                val post = Post(
                    description = description,
                    type = type,
                    latitude = latitude,
                    longitude = longitude,
                    locationName = locationName,
                    authorId = authorId
                )
                postRepository.create(post)
                selectLocation(null) 
            } catch (e: Exception) {
                Log.e("POST", "Erro ao publicar post", e)
            }
        }
    }

    fun toggleLike(postId: String, userId: String) {
        if (userId.isBlank()) return

        val post = postsState.value.find { it.uid == postId } ?: return
        val currentlyLiked = post.likedBy.contains(userId)
        
        // Lógica de Debounce (5 segundos)
        likeJobs[postId]?.cancel()
        likeJobs[postId] = viewModelScope.launch {
            delay(5000)
            
            // O estado final será o oposto do que estava no banco quando o job começou
            // (considerando um número ímpar de cliques durante o delay)
            // Para simplificar e garantir a correção via arrayUnion/arrayRemove:
            postRepository.toggleLike(postId, userId, !currentlyLiked)
            likeJobs.remove(postId)
        }
    }
}
