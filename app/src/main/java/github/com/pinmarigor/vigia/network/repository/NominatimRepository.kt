package github.com.pinmarigor.vigia.network.repository

import android.util.Log
import github.com.pinmarigor.vigia.network.api.NominatimApi
import github.com.pinmarigor.vigia.network.model.LocationInfo

class NominatimRepository(
    private val api: NominatimApi
) {
    suspend fun reverseGeocode(
        latitude: Double,
        longitude: Double
    ): LocationInfo {
        try {
            val response = api.reverseGeocode("jsonv2", latitude, longitude)
            return LocationInfo(
                response.address.city,
                response.address.state,
                response.displayName
            )
        } catch (e: Exception) {
            Log.e("NominatimRepository", "Erro no reverse geocoding: ${e.message}", e)
            return LocationInfo()
        }
    }
}