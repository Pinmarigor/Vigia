package github.com.pinmarigor.vigia.network.model

import com.google.android.gms.maps.model.LatLng

data class RouteResponse(
    val code: String,
    val routes: List<Route>
)

data class Route(
    val distance: Double,
    val duration: Double,
    val geometry: Geometry
)

data class Geometry(
    val coordinates: List<List<Double>>
)