package github.com.pinmarigor.vigia.data.mappers

import github.com.pinmarigor.vigia.data.extensions.toLocalDateTime
import github.com.pinmarigor.vigia.data.extensions.toSosStatus
import github.com.pinmarigor.vigia.data.extensions.toTimestamp
import github.com.pinmarigor.vigia.data.firebase.FBSosAlert
import github.com.pinmarigor.vigia.data.model.SosAlert

// model -> Firebase
fun SosAlert.toFBSosAlert() =
    FBSosAlert(
        uid = uid,
        latitude = latitude,
        longitude = longitude,
        message = message,
        createdAt = createdAt.toTimestamp(),
        status = status.name,
        userId = userId
    )
// Firebase -> model
fun FBSosAlert.toSosAlert() =
    SosAlert(
        uid = uid,
        latitude = latitude,
        longitude = longitude,
        message = message,
        createdAt = createdAt.toLocalDateTime(),
        status = status.toSosStatus(),
        userId = userId
    )