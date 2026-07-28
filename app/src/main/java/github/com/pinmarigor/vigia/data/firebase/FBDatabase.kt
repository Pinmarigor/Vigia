package github.com.pinmarigor.vigia.data.firebase

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
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

    // Crud Usuário
    suspend fun createUser(user: FBUser) {
        usersCollection
            .document(user.uid)
            .set(user)
            .await()
    }
}