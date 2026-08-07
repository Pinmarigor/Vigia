package github.com.pinmarigor.vigia.data.repositories

import github.com.pinmarigor.vigia.data.firebase.FBDatabase
import github.com.pinmarigor.vigia.data.mappers.toFBSosAlert
import github.com.pinmarigor.vigia.data.mappers.toSosAlert
import github.com.pinmarigor.vigia.data.model.SosAlert

class SosAlertRepository(
    private val fbDatabase: FBDatabase
) : Repository<SosAlert> {

    override suspend fun create(item: SosAlert) {
        fbDatabase.createSosAlert(item.toFBSosAlert())
    }

    override suspend fun update(item: SosAlert) {
        fbDatabase.updateSosAlert(item.toFBSosAlert())
    }

    override suspend fun delete(id: String) {
        fbDatabase.deleteSosAlert(id)
    }

    override suspend fun getById(id: String): SosAlert? {
        return fbDatabase.getSosAlertById(id)?.toSosAlert()
    }

    override suspend fun getAll(): List<SosAlert> {
        return fbDatabase.getAllSosAlerts().map { it.toSosAlert() }
    }
}
