package github.com.pinmarigor.vigia.data.firebase

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import github.com.pinmarigor.vigia.data.model.User
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class FBDatabase {
    private val auth = Firebase.auth
    private val firestore = Firebase.firestore

    private val commentsCollection =
        firestore.collection("comments")

    private val emergencyContactsCollection =
        firestore.collection("emergencyContacts")

    private val likesCollection =
        firestore.collection("like")

    private val notificationsCollection =
        firestore.collection("notification")

    private val postsCollection =
        firestore.collection("posts")

    private val sharesCollection =
        firestore.collection("share")

    private val sosAlertsCollection =
        firestore.collection("sosAlert")

    private val usersCollection =
        firestore.collection("users")


    // autenticação
    suspend fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun register(email: String, password: String): String {
        val result =
        auth.createUserWithEmailAndPassword(email, password).await()

        return result.user!!.uid
    }

    fun signOut() {
        auth.signOut()
    }

    fun currentUserUid(): String? =
        auth.currentUser?.uid               // descartar após implementar flow

    suspend fun sendEmailVerification() {
        val user = auth.currentUser ?:throw IllegalArgumentException("Nenhum usuário autenticado")
        user.sendEmailVerification().await()
    }

    suspend fun reloadCurrentUser() {
        val user = auth.currentUser ?: throw IllegalArgumentException("Nenhum usuário autenticado")
        user.reload().await()
    }

    fun isCurrentUserVerified(): Boolean {
        return auth.currentUser?.isEmailVerified == true
    }

    // Crud Usuário
    suspend fun deleteUserDocument(uid: String) {
        usersCollection.document(uid).delete().await()
    }

    suspend fun deleteAuthUser() {
        auth.currentUser?.delete()?.await() ?: throw IllegalStateException("Nenhum usuário autenticado para exclusão")
    }

    suspend fun createUser(user: FBUser) {
        usersCollection
            .document(user.uid)
            .set(user)
            .await()
    }

    suspend fun updateUser(user: User): Boolean? {
        return try {
            usersCollection.document(user.uid)
                .set(user, SetOptions.merge())
                .await()
            Log.d("Firestore", "Usuário atualizado com sucesso!")
            true
        } catch (e: Exception) {
            Log.e("Firestore", "Erro ao atualizar usuário", e)
            false
        }
    }

    suspend fun getUserById(uid: String): FBUser? {
        return try {
            val userReference = usersCollection.document(uid).get().await()
            if (userReference.exists()) {
                userReference.toObject(FBUser::class.java)
            } else {
                Log.d("Firestore", "Nenhum usuário encontrado com esse ID!")
                null
            }
        } catch (e: Exception) {
            Log.e("Firestore", "Erro ao buscar usuário", e)
            null
        }
    }

    // CRUD Posts
    suspend fun createPost(post: FBPost) {
        val doc = postsCollection.document()
        post.uid = doc.id
        doc.set(post).await()
    }

    fun getPostsFlow(): Flow<List<FBPost>> = callbackFlow {
        val listener = postsCollection
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                val posts = snapshot?.toObjects(FBPost::class.java) ?: emptyList()
                trySend(posts)
            }
        awaitClose { listener.remove() }
    }

    suspend fun getPostById(uid: String): FBPost? {
        return try {
            postsCollection.document(uid).get().await().toObject(FBPost::class.java)
        } catch (e: Exception) {
            Log.e("Firestore", "Erro ao buscar post", e)
            null
        }
    }

    suspend fun togglePostLike(postId: String, userId: String, isAdd: Boolean) {
        try {
            val operation = if (isAdd) FieldValue.arrayUnion(userId) else FieldValue.arrayRemove(userId)
            val increment = if (isAdd) 1L else -1L
            
            postsCollection.document(postId).update(
                "likedBy", operation,
                "likeCount", FieldValue.increment(increment)
            ).await()
        } catch (e: Exception) {
            Log.e("Firestore", "Erro ao atualizar likes", e)
        }
    }
}
