package github.com.pinmarigor.vigia.model

import java.time.LocalDateTime

data class Post(
    val id: String = "",
    val description: String = "",
    val authorId: String = "",
    val imageUrls: List<String> = emptyList(),
    val latitude: Double? = null,
    val longitude: Double? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val commemtsCount: Int = 0,
    val shareCount: Int = 0,
    val likeCount: Int = 0,
)
