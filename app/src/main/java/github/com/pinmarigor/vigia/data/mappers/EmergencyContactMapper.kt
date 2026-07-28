package github.com.pinmarigor.vigia.data.mappers

import github.com.pinmarigor.vigia.data.firebase.FBEmergencyContact
import github.com.pinmarigor.vigia.data.extensions.toRelationship
import github.com.pinmarigor.vigia.data.model.EmergencyContact

// model -> Firebase
fun EmergencyContact.toFBEmergencyContact() =
    FBEmergencyContact(
        uid = uid,
        name = name,
        phones = phones,
        relationship = relationship.name,
        linkedUserId = linkedUserId,
        ownerId = ownerId
    )
// Firebase -> model
fun FBEmergencyContact.toEmergencyContact() =
    EmergencyContact(
        uid = uid,
        name = name,
        phones = phones,
        relationship = relationship.toRelationship(),
        linkedUserId = linkedUserId,
        ownerId = ownerId
    )