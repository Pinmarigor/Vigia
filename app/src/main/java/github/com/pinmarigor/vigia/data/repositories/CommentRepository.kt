package github.com.pinmarigor.vigia.data.repositories

import github.com.pinmarigor.vigia.data.firebase.FBDatabase
import github.com.pinmarigor.vigia.data.mappers.toComment
import github.com.pinmarigor.vigia.data.mappers.toFBComment
import github.com.pinmarigor.vigia.data.model.Comment

class CommentRepository(
    private val fbDatabase: FBDatabase
) : Repository<Comment> {

    override suspend fun create(item: Comment) {
        fbDatabase.createComment(item.toFBComment())
    }

    override suspend fun update(item: Comment) {
        fbDatabase.updateComment(item.toFBComment())
    }

    override suspend fun delete(id: String) {
        fbDatabase.deleteComment(id)
    }

    override suspend fun getById(id: String): Comment? {
        return fbDatabase.getCommentById(id)?.toComment()
    }

    override suspend fun getAll(): List<Comment> {
        return fbDatabase.getAllComments().map { it.toComment() }
    }
}
