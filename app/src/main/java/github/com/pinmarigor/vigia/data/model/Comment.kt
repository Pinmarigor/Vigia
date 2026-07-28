package github.com.pinmarigor.vigia.data.model

import java.time.LocalDateTime

data class Comment(
    val uid: String = "",
    val text: String = "",
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val postId: String = "",
    val userId: String = "",
)
