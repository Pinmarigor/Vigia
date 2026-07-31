package github.com.pinmarigor.vigia.data.repositories

import android.util.Log
import github.com.pinmarigor.vigia.data.firebase.FBDatabase
import github.com.pinmarigor.vigia.data.mappers.toFBUser
import github.com.pinmarigor.vigia.data.mappers.toUser
import github.com.pinmarigor.vigia.data.model.User

class UserRepository(
    private val fbDatabase: FBDatabase
) : Repository<User> {

    override suspend fun create(user: User) {
        fbDatabase.createUser(user.toFBUser())
    }

    override suspend fun update(user: User) {
        fbDatabase.updateUser(user)
    }

    override suspend fun delete(id: String) {
        fbDatabase.deleteUserDocument(id)
        fbDatabase.deleteAuthUser()
    }

    override suspend fun getById(id: String): User? {
        return fbDatabase.getUserById(id)?.toUser()
    }

    override suspend fun getAll(): List<User> {
        TODO("Not yet implemented")
    }

    suspend fun signIn(email: String, password: String) {
        fbDatabase.signIn(email, password)
    }

    suspend fun register(name: String, email: String, password: String, phone: String) {
        val uid = fbDatabase.register(email, password)
        val user = User(
            uid = uid,
            name = name,
            email = email,
            phones = if (phone.isBlank()) emptyList() else listOf(phone)
        )
        create(user)
        fbDatabase.sendEmailVerification()
    }

    fun signOut() {
        fbDatabase.signOut()
    }

    fun getCurrentUserUid(): String? {
        return fbDatabase.currentUserUid()
    }

    suspend fun getCurrentUser(): User? {
        val uid = getCurrentUserUid() ?: return null
        return getById(uid)
    }

    suspend fun syncVerification() {
        fbDatabase.reloadCurrentUser()
        if (!fbDatabase.isCurrentUserVerified())
            return
        val uid = fbDatabase.currentUserUid() ?: return
        val user = getById(uid) ?: return
        if (!user.isVerified) {
            update(user.copy(isVerified = true))
        }
    }
}