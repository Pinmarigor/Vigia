package github.com.pinmarigor.vigia.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.com.pinmarigor.vigia.network.model.LocationInfo
import github.com.pinmarigor.vigia.network.model.SearchLocation
import github.com.pinmarigor.vigia.network.repository.NominatimRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class PostViewModel(
    private val repository: NominatimRepository
) : ViewModel() {
    private val _location =
        MutableStateFlow<LocationInfo?>(null)
    val locationState = _location.asStateFlow()

    private val _searchResults = MutableStateFlow<List<SearchLocation>>(emptyList())
    val searchState = _searchResults.asStateFlow()

    private val _selectedLocation = MutableStateFlow<SearchLocation?>(null)
    val selectedLocationState = _selectedLocation.asStateFlow()

    fun reverseGeocode(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                val location = repository.reverseGeocode(lat, lon)
                _location.value = location
            } catch (e: Exception) {
                Log.e("SEARCH","Localização não encontrada", e)
            }
        }
    }

    fun searchLocation(query: String) {
        viewModelScope.launch {
            try {
                val locations = repository.searchLocation(query)
                _searchResults.value = locations
            } catch (e: Exception) {
                Log.e("SEARCH", "Erro ao buscar localidade: $query", e)
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
}
