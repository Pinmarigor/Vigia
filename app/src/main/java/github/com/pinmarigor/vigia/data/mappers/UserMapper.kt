package github.com.pinmarigor.vigia.data.mappers

import github.com.pinmarigor.vigia.data.firebase.FBUser
import github.com.pinmarigor.vigia.data.extensions.toLocalDateTime
import github.com.pinmarigor.vigia.data.model.User
import java.time.LocalDateTime

// Model → Firebase
fun User.toFBUser() = FBUser(
    uid = uid,
    name = name,
    email = email,
    phones = phones,
    photoUrl = photoUrl,
    bio = bio,
    createdAt = null, // Deixa o Firebase definir
    isVerified = isVerified
)
// Firebase → Model
fun FBUser.toUser() = User(
    uid = uid,
    name = name,
    email = email,
    phones = phones,
    photoUrl = photoUrl,
    bio = bio,
    createdAt = createdAt?.toLocalDateTime() ?: LocalDateTime.now(),
    isVerified = isVerified
)
