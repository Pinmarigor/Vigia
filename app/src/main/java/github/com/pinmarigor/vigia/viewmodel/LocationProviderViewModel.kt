package github.com.pinmarigor.vigia.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import github.com.pinmarigor.vigia.data.repositories.LocationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LocationProviderViewModel(
    private val repository: LocationRepository
) : ViewModel() {

    private val _locationState = MutableStateFlow<LatLng?>(null)
    val locationState: StateFlow<LatLng?> = _locationState.asStateFlow()

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState.asStateFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState.asStateFlow()

    fun loadCurrentLocation() {
        viewModelScope.launch {
            _loadingState.value = true
            _errorState.value = null
            try {
                val location = repository.getCurrentLocation()
                if (location != null) {
                    _locationState.value = location
                } else {
                    _errorState.value = "Não foi possível obter a localização atual."
                }
            } catch (e: Exception) {
                _errorState.value = "Erro ao carregar localização: ${e.message}"
            } finally {
                _loadingState.value = false
            }
        }
    }

    fun refreshLocation() {
        loadCurrentLocation()
    }

    fun clearError() {
        _errorState.value = null
    }
}
