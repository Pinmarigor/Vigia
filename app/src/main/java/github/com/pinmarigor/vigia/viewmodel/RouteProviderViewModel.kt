package github.com.pinmarigor.vigia.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import github.com.pinmarigor.vigia.data.repositories.RoutingRepository
import github.com.pinmarigor.vigia.network.model.RouteResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RouteProviderViewModel(
    private val repository: RoutingRepository
) : ViewModel() {

    private val _routes = MutableStateFlow<List<RouteResult>>(emptyList())
    val routes: StateFlow<List<RouteResult>> = _routes.asStateFlow()

    fun calculateRoute(
        origin: LatLng,
        destination: LatLng
    ) {
        viewModelScope.launch {
            try {
                val results = repository.getRoute(origin, destination)
                _routes.value = results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
