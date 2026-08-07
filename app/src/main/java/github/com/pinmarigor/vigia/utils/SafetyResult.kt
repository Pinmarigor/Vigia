package github.com.pinmarigor.vigia.utils

import github.com.pinmarigor.vigia.network.model.SafetyLevel

data class SafetyResult(
    val score: Double,
    val level: SafetyLevel,
    val influencingPosts: Int
)
