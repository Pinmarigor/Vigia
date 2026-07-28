package github.com.pinmarigor.vigia.data.extensions

import github.com.pinmarigor.vigia.data.model.SosStatus

fun String.toSosStatus(): SosStatus =
    SosStatus.entries.firstOrNull {
        it.name == this
    } ?: SosStatus.OUTRO