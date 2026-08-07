package github.com.pinmarigor.vigia.data.mappers

import github.com.pinmarigor.vigia.data.firebase.FBLike
import github.com.pinmarigor.vigia.data.model.Like

// model -> Firebase
fun Like.toFBLike() =
    FBLike(
        uid = uid,
        idUser = idUser,
        idPost = idPost
    )
// Firebase -> model
fun FBLike.toLike() =
    Like(
        uid = uid,
        idUser = idUser,
        idPost = idPost
    )
