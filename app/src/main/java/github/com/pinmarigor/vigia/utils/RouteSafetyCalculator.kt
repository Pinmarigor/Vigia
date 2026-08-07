package github.com.pinmarigor.vigia.utils

import github.com.pinmarigor.vigia.data.model.Post
import github.com.pinmarigor.vigia.data.model.PostType
import github.com.pinmarigor.vigia.network.model.SafetyLevel
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private object RiskWeights {
    const val ROUBO = 10.0
    const val ATIVIDADE_SUSPEITA = 7.0
    const val ILUMINACAO_RUIM = 3.0
    const val OUTRO = 2.0
    const val AREA_SEGURA = 0.0
}

private object RiskThresholds {
    const val LOW = 10.0
    const val MEDIUM = 25.0
}

private object TimeDecay {
    const val MIN_FACTOR = 0.70
    const val MAX_FACTOR = 1.00
    const val MAX_DAYS = 7
}

class RouteSafetyCalculator {

    fun calculateSafety(
        posts: List<Post>
    ): SafetyResult {
        var totalRiskScore = 0.0
        val now = LocalDateTime.now()

        posts.forEach { post ->
            val weight = getWeight(post.type)
            val timeFactor = calculateTimeFactor(post.createdAt, now)
            totalRiskScore += weight * timeFactor
        }

        return SafetyResult(
            score = totalRiskScore,
            level = calculateSafetyLevel(totalRiskScore),
            influencingPosts = posts.size
        )
    }

    private fun getWeight(type: PostType): Double {
        return when (type) {
            PostType.ROUBO -> RiskWeights.ROUBO
            PostType.ATIVIDADE_SUSPEITA -> RiskWeights.ATIVIDADE_SUSPEITA
            PostType.ILUMINACAO_RUIM -> RiskWeights.ILUMINACAO_RUIM
            PostType.AREA_SEGURA -> RiskWeights.AREA_SEGURA
            PostType.OUTRO -> RiskWeights.OUTRO
        }
    }

    private fun calculateTimeFactor(createdAt: LocalDateTime, now: LocalDateTime): Double {
        val daysBetween = ChronoUnit.DAYS.between(createdAt, now).toInt()
        
        if (daysBetween <= 0) return TimeDecay.MAX_FACTOR
        if (daysBetween >= TimeDecay.MAX_DAYS) return TimeDecay.MIN_FACTOR

        val decayPerDay = (TimeDecay.MAX_FACTOR - TimeDecay.MIN_FACTOR) / TimeDecay.MAX_DAYS
        return TimeDecay.MAX_FACTOR - (daysBetween * decayPerDay)
    }

    private fun calculateSafetyLevel(riskScore: Double): SafetyLevel {
        return when {
            riskScore < RiskThresholds.LOW -> SafetyLevel.BAIXO
            riskScore < RiskThresholds.MEDIUM -> SafetyLevel.MEDIO
            else -> SafetyLevel.ALTO
        }
    }
}
