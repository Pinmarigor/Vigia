package github.com.pinmarigor.vigia.data.mappers

import github.com.pinmarigor.vigia.data.firebase.FBUser
import github.com.pinmarigor.vigia.data.extensions.toLocalDateTime
import github.com.pinmarigor.vigia.data.extensions.toTimestamp
import github.com.pinmarigor.vigia.data.model.User

// Model → Firebase
fun User.toFBUser() = FBUser(
    uid = uid,
    name = name,
    email = email,
    phones = phones,
    photoUrl = photoUrl,
    bio = bio,
    createdAt = createdAt.toTimestamp(),
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
    createdAt = createdAt.toLocalDateTime(),
    isVerified = isVerified
)
