package vn.edu.hcmut.furniturestore.model

import com.google.firebase.firestore.DocumentId

data class Category(
    @DocumentId
    val id: String = "",
    val name: String = "",
    val image: String = "" // URL
)
