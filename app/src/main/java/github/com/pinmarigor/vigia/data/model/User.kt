package github.com.pinmarigor.vigia.data.model

import java.time.LocalDateTime

data class User(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val phones: List<String> = emptyList(),
    val photoUrl: String? = null,
    val bio: String? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val isVerified: Boolean = false,
)
