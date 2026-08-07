package github.com.pinmarigor.vigia.utils

import github.com.pinmarigor.vigia.network.model.RouteResult
import java.time.LocalDateTime

data class RouteAnalysis(
    val route: RouteResult,
    val safety: SafetyResult,
    val region: String,
    val neighborhood: String,
    val lastUpdate: LocalDateTime,
    val trend: Double
)
