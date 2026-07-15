package github.com.pinmarigor.vigia.model

import java.time.LocalDateTime

data class Comment(
    val id: String = "",
    val text: String = "",
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val postId: String = "",
    val UserId: String = "",
)
