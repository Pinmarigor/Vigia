package github.com.pinmarigor.vigia.data.mappers

import github.com.pinmarigor.vigia.data.extensions.toLocalDateTime
import github.com.pinmarigor.vigia.data.extensions.toPostType
import github.com.pinmarigor.vigia.data.extensions.toTimestamp
import github.com.pinmarigor.vigia.data.firebase.FBPost
import github.com.pinmarigor.vigia.data.model.Post

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
        createdAt = createdAt.toTimestamp(),
        commemtsCount = commemtsCount,
        shareCount = shareCount,
        likeCount = likeCount,
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
        createdAt = createdAt.toLocalDateTime(),
        commemtsCount = commemtsCount,
        shareCount = shareCount,
        likeCount = likeCount,
        type = type.toPostType()
    )