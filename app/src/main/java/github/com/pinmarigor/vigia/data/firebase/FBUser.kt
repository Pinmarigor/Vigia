package github.com.pinmarigor.vigia.data.firebase

import com.google.firebase.Timestamp

data class FBUser (
    var uid: String = "",
    var name: String = "",
    var email: String = "",
    var phones: List<String> = emptyList(),
    var photoUrl: String? = null,
    var bio: String? = null,
    var createdAt: Timestamp = Timestamp.now(),
    var isVerified: Boolean = false,
)