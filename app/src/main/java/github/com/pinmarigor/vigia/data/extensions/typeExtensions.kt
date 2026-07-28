package github.com.pinmarigor.vigia.data.extensions

import github.com.pinmarigor.vigia.data.model.NotificationType

fun String.toNotificationType(): NotificationType =
    NotificationType.entries.firstOrNull {
        it.name == this
    } ?: NotificationType.OUTRO