package github.com.pinmarigor.vigia.data.firebase

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp

data class FBUser (
    var uid: String = "",
    var name: String = "",
    var email: String = "",
    var phones: List<String> = emptyList(),
    var photoUrl: String? = null,
    var bio: String? = null,
    @ServerTimestamp
    var createdAt: Timestamp? = null,
    var isVerified: Boolean = false,
)
