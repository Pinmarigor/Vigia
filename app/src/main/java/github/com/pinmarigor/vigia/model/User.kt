package github.com.pinmarigor.vigia.model

import java.time.LocalDateTime

data class User(
    val id: String = "",
    val name: String = "",
    val phones: List<String> = emptyList(),
    val email: String = "",
    val photoUrl: String? = "",
    val bio: String? = "",
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val isVerified: Boolean = false,
)
