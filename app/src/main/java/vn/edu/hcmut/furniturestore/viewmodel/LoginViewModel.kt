//package vn.edu.hcmut.furniturestore.viewmodel
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount
//import kotlinx.coroutines.launch
//import vn.edu.hcmut.furniturestore.model.User
//import vn.edu.hcmut.furniturestore.repository.AuthRepository
//
//class LoginViewModel : ViewModel() {
//    private val repository = AuthRepository()
//
//    private val _loginResult = MutableLiveData<Result<User>>()
//    val loginResult: LiveData<Result<User>> = _loginResult
//
//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading
//
//    fun signInWithGoogle(googleSignInAccount: GoogleSignInAccount) {
//        _isLoading.value = true
//        viewModelScope.launch {
//            try {
//                val result = repository.signInWithGoogle(googleSignInAccount)
//                result.fold(
//                    onSuccess = { firebaseUser ->
//                        _loginResult.value = Result.success(repository.mapFirebaseUserToUser(firebaseUser))
//                    },
//                    onFailure = { exception ->
//                        _loginResult.value = Result.failure(exception)
//                    }
//                )
//            } catch (e: Exception) {
//                _loginResult.value = Result.failure(e)
//            } finally {
//                _isLoading.value = false
//            }
//        }
//    }
//
//    fun checkCurrentUser(): User? {
//        val firebaseUser = repository.getCurrentUser()
//        return firebaseUser?.let { repository.mapFirebaseUserToUser(it) }
//    }
//}
package vn.edu.hcmut.furniturestore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.coroutines.launch
import vn.edu.hcmut.furniturestore.model.User
import vn.edu.hcmut.furniturestore.repository.AuthRepository
import vn.edu.hcmut.furniturestore.repository.UserRepository

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val authRepository = AuthRepository(application)
    private val userRepository = UserRepository()

    private val _loginResult = MutableLiveData<Result<User>>()
    val loginResult: LiveData<Result<User>> = _loginResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun signInWithGoogle(googleSignInAccount: GoogleSignInAccount) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val result = authRepository.signInWithGoogle(googleSignInAccount)
                result.fold(
                    onSuccess = { firebaseUser ->
                        val user = authRepository.mapFirebaseUserToUser(firebaseUser)
                        // Save user to Firestore
                        userRepository.saveUser(user)
                        _loginResult.value = Result.success(user)
                    },
                    onFailure = { exception ->
                        _loginResult.value = Result.failure(exception)
                    }
                )
            } catch (e: Exception) {
                _loginResult.value = Result.failure(e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun checkCurrentUser(): User? {
        val firebaseUser = authRepository.getCurrentUser()
        return firebaseUser?.let { authRepository.mapFirebaseUserToUser(it) }
    }
}