package github.com.pinmarigor.vigia.data.firebase

import com.google.firebase.Timestamp
import github.com.pinmarigor.vigia.data.model.NotificationType

data class FBNotification(
    var uid: String = "",
    var receiverId: String = "",
    var senderId: String = "",
    var referenceId: String = "",
    var type: String = NotificationType.OUTRO.name,
    var createdAt: Timestamp = Timestamp.now(),
    var title: String = "",
    var body: String = "",
    var isRead: Boolean = false,
)
