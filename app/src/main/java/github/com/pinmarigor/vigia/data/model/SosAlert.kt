package github.com.pinmarigor.vigia.data.model

import java.time.LocalDateTime

enum class SosStatus {
    PENDENTE, PROCESSANDO, CONCLUIDO, CANCELADO, OUTRO
}

data class SosAlert(
    val uid: String = "",
    val latitude: Double? = null,
    val longitude: Double? = null,
    val message: String = "",
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val status: SosStatus = SosStatus.OUTRO,
    val userId: String = "",
)
