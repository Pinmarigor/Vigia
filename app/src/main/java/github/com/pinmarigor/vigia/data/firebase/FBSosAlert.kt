package github.com.pinmarigor.vigia.data.firebase

import com.google.firebase.Timestamp
import github.com.pinmarigor.vigia.data.model.SosStatus

data class FBSosAlert(
    var uid: String = "",
    var latitude: Double? = null,
    var longitude: Double? = null,
    var message: String = "",
    var createdAt: Timestamp = Timestamp.now(),
    var status: String = SosStatus.OUTRO.name,
    var userId: String = "",
)
