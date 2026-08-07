package github.com.pinmarigor.vigia.network.api

import github.com.pinmarigor.vigia.network.model.RouteResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RoutingApi {

    @GET("route/v1/driving/{coordinates}")
    suspend fun route(
        @Path("coordinates")
        coordinates: String,
        @Query("overview")
        overview: String = "full",
        @Query("geometries")
        geometries: String = "geojson"
    ): RouteResponse
}