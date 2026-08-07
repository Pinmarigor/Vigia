package github.com.pinmarigor.vigia.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import github.com.pinmarigor.vigia.data.repositories.LocationRepository
import github.com.pinmarigor.vigia.viewmodel.LocationProviderViewModel

class LocationProviderViewModelFactory(
    private val repository: LocationRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationProviderViewModel::class.java)) {
            return LocationProviderViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
