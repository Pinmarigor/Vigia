package github.com.pinmarigor.vigia.data.repositories

import github.com.pinmarigor.vigia.data.firebase.FBDatabase
import github.com.pinmarigor.vigia.data.mappers.toEmergencyContact
import github.com.pinmarigor.vigia.data.mappers.toFBEmergencyContact
import github.com.pinmarigor.vigia.data.model.EmergencyContact

class EmergencyContactRepository(
    private val fbDatabase: FBDatabase
) : Repository<EmergencyContact> {

    override suspend fun create(item: EmergencyContact) {
        fbDatabase.createEmergencyContact(item.toFBEmergencyContact())
    }

    override suspend fun update(item: EmergencyContact) {
        fbDatabase.updateEmergencyContact(item.toFBEmergencyContact())
    }

    override suspend fun delete(id: String) {
        fbDatabase.deleteEmergencyContact(id)
    }

    override suspend fun getById(id: String): EmergencyContact? {
        return fbDatabase.getEmergencyContactById(id)?.toEmergencyContact()
    }

    override suspend fun getAll(): List<EmergencyContact> {
        return fbDatabase.getAllEmergencyContacts().map { it.toEmergencyContact() }
    }
}
