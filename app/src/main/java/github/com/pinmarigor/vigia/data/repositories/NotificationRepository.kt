package github.com.pinmarigor.vigia.data.repositories

import github.com.pinmarigor.vigia.data.firebase.FBDatabase
import github.com.pinmarigor.vigia.data.mappers.toFBNotification
import github.com.pinmarigor.vigia.data.mappers.toNotification
import github.com.pinmarigor.vigia.data.model.Notification

class NotificationRepository(
    private val fbDatabase: FBDatabase
) : Repository<Notification> {

    override suspend fun create(item: Notification) {
        fbDatabase.createNotification(item.toFBNotification())
    }

    override suspend fun update(item: Notification) {
        fbDatabase.updateNotification(item.toFBNotification())
    }

    override suspend fun delete(id: String) {
        fbDatabase.deleteNotification(id)
    }

    override suspend fun getById(id: String): Notification? {
        return fbDatabase.getNotificationById(id)?.toNotification()
    }

    override suspend fun getAll(): List<Notification> {
        return fbDatabase.getAllNotifications().map { it.toNotification() }
    }
}
