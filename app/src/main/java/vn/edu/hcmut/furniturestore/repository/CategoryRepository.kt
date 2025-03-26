package vn.edu.hcmut.furniturestore.repository


import com.google.firebase.firestore.FirebaseFirestore
import vn.edu.hcmut.furniturestore.model.Category

class CategoryRepository {
    private val db = FirebaseFirestore.getInstance()

    fun getCategories(onResult: (List<Category>) -> Unit, onError: (Exception) -> Unit) {
        db.collection("categories").get()
            .addOnSuccessListener { result ->
                val categories = result.documents.mapNotNull { it.toObject(Category::class.java) }
                onResult(categories)
            }
            .addOnFailureListener { exception ->
                onError(exception)
            }
    }
}
