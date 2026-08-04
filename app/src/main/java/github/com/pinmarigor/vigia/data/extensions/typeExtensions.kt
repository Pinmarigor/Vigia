package github.com.pinmarigor.vigia.data.extensions

import github.com.pinmarigor.vigia.data.model.NotificationType
import github.com.pinmarigor.vigia.data.model.PostType

fun String.toNotificationType(): NotificationType =
    NotificationType.entries.firstOrNull {
        it.name == this
    } ?: NotificationType.OUTRO

fun String.toPostType(): PostType =
    PostType.entries.firstOrNull {
        it.name == this
    } ?: PostType.OUTRO