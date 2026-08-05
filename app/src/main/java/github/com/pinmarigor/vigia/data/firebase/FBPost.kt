package github.com.pinmarigor.vigia.data.firebase

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp
import github.com.pinmarigor.vigia.data.model.PostType

data class FBPost(
    var uid: String = "",
    var description: String = "",
    var authorId: String = "",
    var imageUrls: List<String> = emptyList(),
    var latitude: Double? = null,
    var longitude: Double? = null,
    val locationName: String = "",
    @ServerTimestamp
    var createdAt: Timestamp? = null,
    var commentsCount: Int = 0,
    var shareCount: Int = 0,
    var likeCount: Int = 0,
    var likedBy: List<String> = emptyList(),
    var type: String = PostType.OUTRO.name
)
