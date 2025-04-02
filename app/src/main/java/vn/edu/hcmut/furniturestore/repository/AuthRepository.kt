//package vn.edu.hcmut.furniturestore.repository
//
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.FirebaseUser
//import com.google.firebase.auth.GoogleAuthProvider
//import kotlinx.coroutines.tasks.await
//import vn.edu.hcmut.furniturestore.model.User
//
//class AuthRepository {
//    private val firebaseAuth = FirebaseAuth.getInstance()
//
//    suspend fun signInWithGoogle(googleSignInAccount: GoogleSignInAccount): Result<FirebaseUser> {
//        return try {
//            val credential = GoogleAuthProvider.getCredential(googleSignInAccount.idToken, null)
//            val authResult = firebaseAuth.signInWithCredential(credential).await()
//            Result.success(authResult.user!!)
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//
//    fun getCurrentUser(): FirebaseUser? {
//        return firebaseAuth.currentUser
//    }
//
//    fun mapFirebaseUserToUser(firebaseUser: FirebaseUser): User {
//        return User(
//            uid = firebaseUser.uid,
//            email = firebaseUser.email,
//            displayName = firebaseUser.displayName,
//            photoUrl = firebaseUser.photoUrl?.toString()
//        )
//    }
//
//    fun signOut() {
//        firebaseAuth.signOut()
//    }
//}
package vn.edu.hcmut.furniturestore.repository

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import vn.edu.hcmut.furniturestore.R
import vn.edu.hcmut.furniturestore.model.User

class AuthRepository(private val context: Context) {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private lateinit var googleSignInClient: GoogleSignInClient

    init {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(context, gso)
    }

    suspend fun signInWithGoogle(googleSignInAccount: GoogleSignInAccount): Result<FirebaseUser> {
        return try {
            val credential = GoogleAuthProvider.getCredential(googleSignInAccount.idToken, null)
            val authResult = firebaseAuth.signInWithCredential(credential).await()
            Result.success(authResult.user!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    fun mapFirebaseUserToUser(firebaseUser: FirebaseUser): User {
        return User(
            uid = firebaseUser.uid,
            email = firebaseUser.email,
            displayName = firebaseUser.displayName,
            photoUrl = firebaseUser.photoUrl?.toString()
        )
    }

    suspend fun signOut(): Result<Unit> {
        return try {
            firebaseAuth.signOut()
            googleSignInClient.signOut().await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}