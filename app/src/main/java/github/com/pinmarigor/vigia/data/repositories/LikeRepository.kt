package github.com.pinmarigor.vigia.data.repositories

import github.com.pinmarigor.vigia.data.firebase.FBDatabase
import github.com.pinmarigor.vigia.data.mappers.toFBLike
import github.com.pinmarigor.vigia.data.mappers.toLike
import github.com.pinmarigor.vigia.data.model.Like

class LikeRepository(
    private val fbDatabase: FBDatabase
) : Repository<Like> {

    override suspend fun create(item: Like) {
        fbDatabase.createLike(item.toFBLike())
    }

    override suspend fun update(item: Like) {
        fbDatabase.updateLike(item.toFBLike())
    }

    override suspend fun delete(id: String) {
        fbDatabase.deleteLike(id)
    }

    override suspend fun getById(id: String): Like? {
        return fbDatabase.getLikeById(id)?.toLike()
    }

    override suspend fun getAll(): List<Like> {
        return fbDatabase.getAllLikes().map { it.toLike() }
    }
}
