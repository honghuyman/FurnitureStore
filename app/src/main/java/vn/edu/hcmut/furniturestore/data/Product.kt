//package vn.edu.hcmut.furniturestore.data
//
//data class Product(
//    val id: Int,
//    val image: Int,
//    var name: String,
//    var description: String,
//    var modelName: String,
//    var price: Float = 0f
//)
package vn.edu.hcmut.furniturestore.data

import androidx.annotation.DrawableRes

data class Product(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    @DrawableRes val image: Int,
    val modelName: String,
    val width: Int? = null,     // Width in cm
    val height: Int? = null,    // Height in cm
    val depth: Int? = null,     // Depth/Length in cm
    val material: String? = null // Materials used
)