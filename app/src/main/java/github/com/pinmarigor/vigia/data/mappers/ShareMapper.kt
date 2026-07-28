package github.com.pinmarigor.vigia.data.mappers

import github.com.pinmarigor.vigia.data.firebase.FBShare
import github.com.pinmarigor.vigia.data.model.Share

// model -> Firebase
fun Share.toFBShare() =
    FBShare(
        idUser = idUser,
        idPost = idPost,
    )
// Firebase -> model
fun FBShare.toShare() =
    Share(
        idUser = idUser,
        idPost = idPost,
    )