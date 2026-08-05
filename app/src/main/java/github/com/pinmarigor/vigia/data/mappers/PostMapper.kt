package github.com.pinmarigor.vigia.data.mappers

import github.com.pinmarigor.vigia.data.extensions.toLocalDateTime
import github.com.pinmarigor.vigia.data.extensions.toPostType
import github.com.pinmarigor.vigia.data.firebase.FBPost
import github.com.pinmarigor.vigia.data.model.Post
import java.time.LocalDateTime

// model -> Firebase
fun Post.toFBPost() =
    FBPost(
        uid = uid,
        description = description,
        authorId = authorId,
        imageUrls = imageUrls,
        latitude = latitude,
        longitude = longitude,
        locationName = locationName,
        createdAt = null, // Deixa o Firebase definir o horário do servidor
        commentsCount = commentsCount,
        shareCount = shareCount,
        likeCount = likeCount,
        likedBy = likedBy,
        type = type.name
    )
// Firebase -> model
fun FBPost.toPost() =
    Post(
        uid = uid,
        description = description,
        authorId = authorId,
        imageUrls = imageUrls,
        latitude = latitude,
        longitude = longitude,
        locationName = locationName,
        createdAt = createdAt?.toLocalDateTime() ?: LocalDateTime.now(),
        commentsCount = commentsCount,
        shareCount = shareCount,
        likeCount = likeCount,
        likedBy = likedBy,
        type = type.toPostType()
    )