package github.com.pinmarigor.vigia.network.model

import com.google.android.gms.maps.model.LatLng
import github.com.pinmarigor.vigia.utils.SafetyResult

enum class SafetyLevel {
    BAIXO,
    MEDIO,
    ALTO
}

data class RouteResult(
    val points: List<LatLng>,
    val distanceMeters: Double,
    val durationSeconds: Double,
    val safety: SafetyResult
)
