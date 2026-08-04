package github.com.pinmarigor.vigia.network.repository

import android.util.Log
import github.com.pinmarigor.vigia.network.api.NominatimApi
import github.com.pinmarigor.vigia.network.model.LocationInfo
import github.com.pinmarigor.vigia.network.model.SearchLocation

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

    suspend fun searchLocation(query: String): MutableList<SearchLocation> {
        try {

            val q = api.searchLocation(query)
            return q.map {
                SearchLocation(
                    it.displayName,
                    it.lat.toDouble(),
                    it.lon.toDouble()
                )
            }.toMutableList()

        } catch (e: Exception) {
            Log.e("NominatimRepository", "Erro no reverse geocoding: ${e.message}", e)
            return mutableListOf<SearchLocation>()
        }
    }
}