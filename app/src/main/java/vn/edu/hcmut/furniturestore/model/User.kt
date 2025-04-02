package vn.edu.hcmut.furniturestore.model

data class User(
    val uid: String,
    val email: String? = null,
    val displayName: String? = null,
    val photoUrl: String? = null
) {
    // Secondary constructor for empty initialization if needed
    constructor() : this(
        uid = "",
        email = null,
        displayName = null,
        photoUrl = null
    )

//    // Getter methods - Kotlin automatically generates these for data classes,
//    // but we're adding them explicitly to resolve the errors
//    fun getUid(): String = uid
//    fun getEmail(): String? = email
//    fun getDisplayName(): String? = displayName
//    fun getPhotoUrl(): String? = photoUrl
}