package github.com.pinmarigor.vigia.data.mappers

import github.com.pinmarigor.vigia.data.extensions.toLocalDateTime
import github.com.pinmarigor.vigia.data.extensions.toTimestamp
import github.com.pinmarigor.vigia.data.firebase.FBComment
import github.com.pinmarigor.vigia.data.model.Comment

// model -> Firebase
fun Comment.toFBComment() =
    FBComment(
        uid = uid,
        text = text,
        createdAt = createdAt.toTimestamp(),
        postId = postId,
        userId = userId,
    )
// Firebase -> moodel
fun FBComment.toComment() =
    Comment(
        uid = uid,
        text = text,
        createdAt = createdAt.toLocalDateTime(),
        postId = postId,
        userId = userId,
    )