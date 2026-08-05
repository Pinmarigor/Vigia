package github.com.pinmarigor.vigia.data.model

import java.time.LocalDateTime

enum class PostType {
    ATIVIDADE_SUSPEITA, ROUBO, ILUMINACAO_RUIM, AREA_SEGURA, OUTRO
}
data class Post(
    val uid: String = "",
    val description: String = "",
    val authorId: String = "",
    val imageUrls: List<String> = emptyList(),
    val latitude: Double? = null,
    val longitude: Double? = null,
    val locationName: String = "",
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val commentsCount: Int = 0,
    val shareCount: Int = 0,
    val likeCount: Int = 0,
    val likedBy: List<String> = emptyList(),
    val type: PostType = PostType.OUTRO
)
