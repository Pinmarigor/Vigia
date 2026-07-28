package github.com.pinmarigor.vigia.data.mappers

import github.com.pinmarigor.vigia.data.extensions.toLocalDateTime
import github.com.pinmarigor.vigia.data.extensions.toNotificationType
import github.com.pinmarigor.vigia.data.extensions.toTimestamp
import github.com.pinmarigor.vigia.data.firebase.FBNotification
import github.com.pinmarigor.vigia.data.model.Notification

// model -> Firebase
fun Notification.toFBNotification() =
    FBNotification(
        uid = uid,
        receiverId = receiverId,
        senderId = senderId,
        referenceId = referenceId,
        type = type.name,
        createdAt = createdAt.toTimestamp(),
        title = title,
        body = body,
        isRead = isRead,
    )
// Firebase -> model
fun FBNotification.toNotification() =
    Notification(
        uid = uid,
        receiverId = receiverId,
        senderId = senderId,
        referenceId = referenceId,
        type = type.toNotificationType(),
        createdAt = createdAt.toLocalDateTime(),
        title = title,
        body = body,
        isRead = isRead,
    )