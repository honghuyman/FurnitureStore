package vn.edu.hcmut.furniturestore.data

import vn.edu.hcmut.furniturestore.R

val fabrics = listOf(
    FabricItem("off_white", R.drawable.fabric_off_wihite),
    FabricItem("ash", R.drawable.fabric_ash),
    FabricItem("blue", R.drawable.fabric_blue),
    FabricItem("red", R.drawable.fabric_red),
)

val products = listOf(
    Product(0, R.drawable.double_bed, "Double bed", "Wooden", "double_bed", 25_000f),
    Product(1, R.drawable.standing_desk, "Standing desk", "Metallic", "standing_desk", 12_000f),
    Product(2, R.drawable.dining_table, "Dining table", "Wooden", "dining_table", 7_000f),
    Product(3, R.drawable.folding_table, "Folding table", "Ceramic", "folding_table", 5_000f),
    Product(4, R.drawable.tuxedo_sofa, "Tuxedo sofa", "Faux leather", "tuxedo_sofa", 15_000f),
    Product(5, R.drawable.apartment_sofa, "Apartment sofa", "Ceramic", "apartment_sofa", 18_000f),
    Product(6, R.drawable.dining_table_set, "Dining table", "Wooden", "dining_table_set", 12_000f),
    Product(7, R.drawable.corner_sofa, "Corner sofa", "Poliform", "corner_sofa", 8_000f),
    Product(8, R.drawable.three_seater_sofa, "Three seater sofa", "Poliform", "three_seater_sofa", 20_000f),
    Product(9, R.drawable.bauhaus_chair, "Bauhaus Chair", "Leather", "bauhaus_chair", 6_000f),
)

data class FabricItem(var name: String, var imageId: Int)