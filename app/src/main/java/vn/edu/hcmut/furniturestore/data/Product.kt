package vn.edu.hcmut.furniturestore.data

data class Product(
    val id: Int,
    val image: Int,
    var name: String,
    var description: String,
    var modelName: String,
    var price: Float = 0f
)