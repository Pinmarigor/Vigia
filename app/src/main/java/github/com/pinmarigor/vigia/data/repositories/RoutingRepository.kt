package github.com.pinmarigor.vigia.data.repositories

import com.google.android.gms.maps.model.LatLng
import github.com.pinmarigor.vigia.network.api.RoutingApi
import github.com.pinmarigor.vigia.network.model.RouteResult
import github.com.pinmarigor.vigia.utils.RouteSafetyCalculator

class RoutingRepository(
    private val routingApi: RoutingApi,
    private val postsRepository: PostRepository,
    private val safetyCalculator: RouteSafetyCalculator
) {
    suspend fun getRoute(
        origin: LatLng,
        destination: LatLng
    ): List<RouteResult> {

        val coordinates =
            "${origin.longitude},${origin.latitude};" +
                    "${destination.longitude},${destination.latitude}"

        val response = routingApi.route(coordinates)

        return response.routes.map { route ->
            // 1. Obter geometria
            val points = route.geometry.coordinates.map { LatLng(it[1], it[0]) }
            
            // 2. Buscar Posts candidatos
            val posts = postsRepository.getPostsNearRoute(points)

            // 3. Calcular segurança
            val safetyResult = safetyCalculator.calculateSafety(posts)

            // 4. Montar RouteResult
            RouteResult(
                points = points,
                distanceMeters = route.distance,
                durationSeconds = route.duration,
                safety = safetyResult
            )
        }
    }
}
