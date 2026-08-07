package github.com.pinmarigor.vigia.data.repositories

import github.com.pinmarigor.vigia.data.firebase.FBDatabase
import github.com.pinmarigor.vigia.data.mappers.toFBPost
import github.com.pinmarigor.vigia.data.mappers.toPost
import github.com.pinmarigor.vigia.data.model.Post
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PostRepository(
    private val fbDatabase: FBDatabase
) : Repository<Post> {

    companion object {
        private const val SEARCH_PADDING = 0.005
    }

    override suspend fun create(item: Post) {
        fbDatabase.createPost(item.toFBPost())
    }

    override suspend fun update(item: Post) {
        fbDatabase.updatePost(item.toFBPost())
    }

    override suspend fun delete(id: String) {
        fbDatabase.deletePost(id)
    }

    override suspend fun getById(id: String): Post? {
        return fbDatabase.getPostById(id)?.toPost()
    }

    override suspend fun getAll(): List<Post> {
        return fbDatabase.getAllPosts().map { it.toPost() }
    }

    fun getPostsFlow(): Flow<List<Post>> {
        return fbDatabase.getPostsFlow().map { list ->
            list.map { it.toPost() }
        }
    }

    suspend fun toggleLike(postId: String, userId: String, isAdd: Boolean) {
        fbDatabase.togglePostLike(postId, userId, isAdd)
    }

    suspend fun getRecentPosts(): List<Post> {
        return fbDatabase.getRecentPosts().map { it.toPost() }
    }

    suspend fun getPostsNearRoute(
        route: List<LatLng>
    ): List<Post> {
        if (route.isEmpty()) return emptyList()

        val minLat = route.minOf { it.latitude }
        val maxLat = route.maxOf { it.latitude }
        val minLng = route.minOf { it.longitude }
        val maxLng = route.maxOf { it.longitude }

        val padding = SEARCH_PADDING

        return getRecentPosts().filter { post ->
            val lat = post.latitude ?: return@filter false
            val lng = post.longitude ?: return@filter false

            lat in (minLat - padding)..(maxLat + padding) &&
            lng in (minLng - padding)..(maxLng + padding)
        }
    }
}
