package github.com.pinmarigor.vigia.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.com.pinmarigor.vigia.network.model.LocationInfo
import github.com.pinmarigor.vigia.network.repository.NominatimRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface LocationState {
    object Idle
    object Loading
    data class Success(val location: LocationInfo)
    data class Error(val error: String)
}

class PostViewModel(
    private val repository: NominatimRepository
) : ViewModel() {
    private val _location =
        MutableStateFlow<LocationInfo?>(null)
    val location = _location.asStateFlow()

    fun reverseGeocode(lat: Double, lon: Double) {
        viewModelScope.launch {
            val location = repository.reverseGeocode(lat, lon)
            _location.value = location
        }
    }
}