package github.com.pinmarigor.vigia.network.api

import github.com.pinmarigor.vigia.network.dto.NominatimResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NominatimApi {
    @GET("reverse")
    suspend fun reverseGeocode(
        @Query("format")
        format: String = "jsonv2",
        @Query("lat")
        latitude: Double,
        @Query("lon")
        longitude: Double
    ): NominatimResponse

    @GET("search")
    suspend fun searchLocation(
        @Query("q")
        query: String,
        @Query("format")
        format: String = "jsonv2"
    ): List<NominatimResponse>
}
