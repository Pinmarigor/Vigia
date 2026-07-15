package github.com.pinmarigor.vigia.model

import java.time.LocalDateTime

enum class NotificationType {
    LIKE, COMMENT, SHARE, SOS, OUTRO
}

data class Notification(
    val id: String = "",
    val receiverId: String = "",
    val senderId: String = "",
    val referenceId: String = "",
    val type: NotificationType = NotificationType.OUTRO,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val title: String = "",
    val body: String = "",
    val isRead: Boolean = false,
)
