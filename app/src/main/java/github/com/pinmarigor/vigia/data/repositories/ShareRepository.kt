package github.com.pinmarigor.vigia.data.repositories

import github.com.pinmarigor.vigia.data.firebase.FBDatabase
import github.com.pinmarigor.vigia.data.mappers.toFBShare
import github.com.pinmarigor.vigia.data.mappers.toShare
import github.com.pinmarigor.vigia.data.model.Share

class ShareRepository(
    private val fbDatabase: FBDatabase
) : Repository<Share> {

    override suspend fun create(item: Share) {
        fbDatabase.createShare(item.toFBShare())
    }

    override suspend fun update(item: Share) {
        fbDatabase.updateShare(item.toFBShare())
    }

    override suspend fun delete(id: String) {
        fbDatabase.deleteShare(id)
    }

    override suspend fun getById(id: String): Share? {
        return fbDatabase.getShareById(id)?.toShare()
    }

    override suspend fun getAll(): List<Share> {
        return fbDatabase.getAllShares().map { it.toShare() }
    }
}
