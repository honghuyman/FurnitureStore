package vn.edu.hcmut.furniturestore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vn.edu.hcmut.furniturestore.model.User
import vn.edu.hcmut.furniturestore.repository.AuthRepository
import vn.edu.hcmut.furniturestore.repository.UserRepository

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val authRepository = AuthRepository(application)
    private val userRepository = UserRepository()

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _signOutResult = MutableLiveData<Result<Unit>>()
    val signOutResult: LiveData<Result<Unit>> = _signOutResult

    fun loadUserProfile() {
        _isLoading.value = true
        val currentUser = authRepository.getCurrentUser()
        if (currentUser != null) {
            viewModelScope.launch {
                val userResult = userRepository.getUser(currentUser.uid)
                if (userResult.isSuccess) {
                    _user.value = userResult.getOrNull()
                } else {
                    // If user not found in Firestore, use data from Firebase Auth
                    _user.value = authRepository.mapFirebaseUserToUser(currentUser)
                    // Save user to Firestore
                    val newUser = authRepository.mapFirebaseUserToUser(currentUser)
                    userRepository.saveUser(newUser)
                }
                _isLoading.value = false
            }
        } else {
            _user.value = null
            _isLoading.value = false
        }
    }

    fun signOut() {
        _isLoading.value = true
        viewModelScope.launch {
            _signOutResult.value = authRepository.signOut()
            _isLoading.value = false
        }
    }
}