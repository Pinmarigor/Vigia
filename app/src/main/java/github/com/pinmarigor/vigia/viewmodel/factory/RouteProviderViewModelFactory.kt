package github.com.pinmarigor.vigia.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import github.com.pinmarigor.vigia.data.repositories.RoutingRepository
import github.com.pinmarigor.vigia.viewmodel.RouteProviderViewModel

class RouteProviderViewModelFactory(
    private val repository: RoutingRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RouteProviderViewModel::class.java)) {
            return RouteProviderViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
