package github.com.pinmarigor.vigia.data.firebase

import com.google.firebase.Timestamp

data class FBComment(
    var uid: String = "",
    var text: String = "",
    var createdAt: Timestamp = Timestamp.now(),
    var postId: String = "",
    var userId: String = "",
)
