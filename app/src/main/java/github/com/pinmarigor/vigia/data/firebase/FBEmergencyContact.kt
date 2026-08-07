package github.com.pinmarigor.vigia.data.firebase

import github.com.pinmarigor.vigia.data.model.Relationship

data class FBEmergencyContact (
    var uid: String = "",
    val name: String = "",
    val phones: List<String> = emptyList(),
    val relationship: String = Relationship.OUTRO.name,
    val linkedUserId: String? = null,
    val ownerId: String = "",
)