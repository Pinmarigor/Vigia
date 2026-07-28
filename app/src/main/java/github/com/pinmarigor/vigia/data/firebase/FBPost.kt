package github.com.pinmarigor.vigia.data.firebase

import com.google.firebase.Timestamp

data class FBPost(
    var uid: String = "",
    var description: String = "",
    var authorId: String = "",
    var imageUrls: List<String> = emptyList(),
    var latitude: Double? = null,
    var longitude: Double? = null,
    var createdAt: Timestamp = Timestamp.now(),
    var commemtsCount: Int = 0,
    var shareCount: Int = 0,
    var likeCount: Int = 0,
)
