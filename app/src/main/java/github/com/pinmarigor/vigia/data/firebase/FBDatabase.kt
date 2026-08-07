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

    suspend fun getAllUsers(): List<FBUser> {
        return try {
            usersCollection.get().await().toObjects(FBUser::class.java)
        } catch (e: Exception) {
            Log.e("Firestore", "Erro ao buscar todos os usuários", e)
            emptyList()
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

    suspend fun updatePost(post: FBPost) {
        postsCollection.document(post.uid).set(post, SetOptions.merge()).await()
    }

    suspend fun deletePost(uid: String) {
        postsCollection.document(uid).delete().await()
    }

    suspend fun getAllPosts(): List<FBPost> {
        return try {
            postsCollection.get().await().toObjects(FBPost::class.java)
        } catch (e: Exception) {
            Log.e("Firestore", "Erro ao buscar posts", e)
            emptyList()
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

    suspend fun getRecentPosts(): List<FBPost> {
        val RECENT_POST_DAY = 24
        val RECENT_POSTS_WEEK = 7
        val RECENT_POST_MONTH = 30

        val last24Hours = System.currentTimeMillis() - (RECENT_POST_DAY * 60 * 60 * 1000)
        val lastWeek = System.currentTimeMillis() - (RECENT_POSTS_WEEK * RECENT_POST_DAY * 60 * 60 * 1000)
        val lastMonth = System.currentTimeMillis() - (RECENT_POST_MONTH * RECENT_POST_DAY * 60 * 60 * 1000)

        val since = com.google.firebase.Timestamp(java.util.Date(lastWeek))

        return try {
            postsCollection
                .whereGreaterThanOrEqualTo("createdAt", since)
                .get()
                .await()
                .toObjects(FBPost::class.java)
        } catch (e: Exception) {
            Log.e("Firestore", "Erro ao buscar posts recentes", e)
            emptyList()
        }
    }

    // CRUD Comments
    suspend fun createComment(comment: FBComment) {
        val doc = commentsCollection.document()
        comment.uid = doc.id
        doc.set(comment).await()
    }

    suspend fun updateComment(comment: FBComment) {
        commentsCollection.document(comment.uid).set(comment, SetOptions.merge()).await()
    }

    suspend fun deleteComment(uid: String) {
        commentsCollection.document(uid).delete().await()
    }

    suspend fun getCommentById(uid: String): FBComment? {
        return try {
            commentsCollection.document(uid).get().await().toObject(FBComment::class.java)
        } catch (e: Exception) {
            Log.e("Firestore", "Erro ao buscar comentário", e)
            null
        }
    }

    suspend fun getAllComments(): List<FBComment> {
        return try {
            commentsCollection.get().await().toObjects(FBComment::class.java)
        } catch (e: Exception) {
            Log.e("Firestore", "Erro ao buscar comentários", e)
            emptyList()
        }
    }

    // CRUD EmergencyContacts
    suspend fun createEmergencyContact(contact: FBEmergencyContact) {
        val doc = emergencyContactsCollection.document()
        contact.uid = doc.id
        doc.set(contact).await()
    }

    suspend fun updateEmergencyContact(contact: FBEmergencyContact) {
        emergencyContactsCollection.document(contact.uid).set(contact, SetOptions.merge()).await()
    }

    suspend fun deleteEmergencyContact(uid: String) {
        emergencyContactsCollection.document(uid).delete().await()
    }

    suspend fun getEmergencyContactById(uid: String): FBEmergencyContact? {
        return try {
            emergencyContactsCollection.document(uid).get().await().toObject(FBEmergencyContact::class.java)
        } catch (e: Exception) {
            Log.e("Firestore", "Erro ao buscar contato de emergência", e)
            null
        }
    }

    suspend fun getAllEmergencyContacts(): List<FBEmergencyContact> {
        return try {
            emergencyContactsCollection.get().await().toObjects(FBEmergencyContact::class.java)
        } catch (e: Exception) {
            Log.e("Firestore", "Erro ao buscar contatos de emergência", e)
            emptyList()
        }
    }

    // CRUD Likes
    suspend fun createLike(like: FBLike) {
        val doc = likesCollection.document()
        like.uid = doc.id
        doc.set(like).await()
    }

    suspend fun updateLike(like: FBLike) {
        likesCollection.document(like.uid).set(like, SetOptions.merge()).await()
    }

    suspend fun deleteLike(uid: String) {
        likesCollection.document(uid).delete().await()
    }

    suspend fun getLikeById(uid: String): FBLike? {
        return try {
            likesCollection.document(uid).get().await().toObject(FBLike::class.java)
        } catch (e: Exception) {
            Log.e("Firestore", "Erro ao buscar like", e)
            null
        }
    }

    suspend fun getAllLikes(): List<FBLike> {
        return try {
            likesCollection.get().await().toObjects(FBLike::class.java)
        } catch (e: Exception) {
            Log.e("Firestore", "Erro ao buscar likes", e)
            emptyList()
        }
    }

    // CRUD Notifications
    suspend fun createNotification(notification: FBNotification) {
        val doc = notificationsCollection.document()
        notification.uid = doc.id
        doc.set(notification).await()
    }

    suspend fun updateNotification(notification: FBNotification) {
        notificationsCollection.document(notification.uid).set(notification, SetOptions.merge()).await()
    }

    suspend fun deleteNotification(uid: String) {
        notificationsCollection.document(uid).delete().await()
    }

    suspend fun getNotificationById(uid: String): FBNotification? {
        return try {
            notificationsCollection.document(uid).get().await().toObject(FBNotification::class.java)
        } catch (e: Exception) {
            Log.e("Firestore", "Erro ao buscar notificação", e)
            null
        }
    }

    suspend fun getAllNotifications(): List<FBNotification> {
        return try {
            notificationsCollection.get().await().toObjects(FBNotification::class.java)
        } catch (e: Exception) {
            Log.e("Firestore", "Erro ao buscar notificações", e)
            emptyList()
        }
    }

    // CRUD Shares
    suspend fun createShare(share: FBShare) {
        val doc = sharesCollection.document()
        share.uid = doc.id
        doc.set(share).await()
    }

    suspend fun updateShare(share: FBShare) {
        sharesCollection.document(share.uid).set(share, SetOptions.merge()).await()
    }

    suspend fun deleteShare(uid: String) {
        sharesCollection.document(uid).delete().await()
    }

    suspend fun getShareById(uid: String): FBShare? {
        return try {
            sharesCollection.document(uid).get().await().toObject(FBShare::class.java)
        } catch (e: Exception) {
            Log.e("Firestore", "Erro ao buscar compartilhamento", e)
            null
        }
    }

    suspend fun getAllShares(): List<FBShare> {
        return try {
            sharesCollection.get().await().toObjects(FBShare::class.java)
        } catch (e: Exception) {
            Log.e("Firestore", "Erro ao buscar compartilhamentos", e)
            emptyList()
        }
    }

    // CRUD SosAlerts
    suspend fun createSosAlert(alert: FBSosAlert) {
        val doc = sosAlertsCollection.document()
        alert.uid = doc.id
        doc.set(alert).await()
    }

    suspend fun updateSosAlert(alert: FBSosAlert) {
        sosAlertsCollection.document(alert.uid).set(alert, SetOptions.merge()).await()
    }

    suspend fun deleteSosAlert(uid: String) {
        sosAlertsCollection.document(uid).delete().await()
    }

    suspend fun getSosAlertById(uid: String): FBSosAlert? {
        return try {
            sosAlertsCollection.document(uid).get().await().toObject(FBSosAlert::class.java)
        } catch (e: Exception) {
            Log.e("Firestore", "Erro ao buscar alerta SOS", e)
            null
        }
    }

    suspend fun getAllSosAlerts(): List<FBSosAlert> {
        return try {
            sosAlertsCollection.get().await().toObjects(FBSosAlert::class.java)
        } catch (e: Exception) {
            Log.e("Firestore", "Erro ao buscar alertas SOS", e)
            emptyList()
        }
    }
}
