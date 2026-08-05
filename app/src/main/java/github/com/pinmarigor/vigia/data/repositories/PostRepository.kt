package github.com.pinmarigor.vigia.data.repositories

import github.com.pinmarigor.vigia.data.firebase.FBDatabase
import github.com.pinmarigor.vigia.data.mappers.toFBPost
import github.com.pinmarigor.vigia.data.mappers.toPost
import github.com.pinmarigor.vigia.data.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PostRepository(
    private val fbDatabase: FBDatabase
) : Repository<Post> {

    override suspend fun create(item: Post) {
        fbDatabase.createPost(item.toFBPost())
    }

    override suspend fun update(item: Post) {
        // Implementar futuramente se necessário
    }

    override suspend fun delete(id: String) {
        // Implementar futuramente se necessário
    }

    override suspend fun getById(id: String): Post? {
        return fbDatabase.getPostById(id)?.toPost()
    }

    override suspend fun getAll(): List<Post> {
        // Para listagem reativa, usamos o getPostsFlow
        return emptyList() 
    }

    fun getPostsFlow(): Flow<List<Post>> {
        return fbDatabase.getPostsFlow().map { list ->
            list.map { it.toPost() }
        }
    }

    suspend fun toggleLike(postId: String, userId: String, isAdd: Boolean) {
        fbDatabase.togglePostLike(postId, userId, isAdd)
    }
}
