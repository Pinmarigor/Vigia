package github.com.pinmarigor.vigia.data.repositories

import github.com.pinmarigor.vigia.data.firebase.FBDatabase
import github.com.pinmarigor.vigia.data.mappers.toFBUser
import github.com.pinmarigor.vigia.data.model.User

class UserRepository(
    private val fbDatabase: FBDatabase
) : Repository<User> {

    override suspend fun create(user: User) {
        fbDatabase.createUser(user.toFBUser())
    }

    override suspend fun update(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: String): User? {
        TODO("Not yet implemented")
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
    }

    fun signOut() {
        fbDatabase.signOut()
    }

}