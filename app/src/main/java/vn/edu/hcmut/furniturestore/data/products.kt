package vn.edu.hcmut.furniturestore.data

import vn.edu.hcmut.furniturestore.R

// Sample products data
val products = listOf(
    Product(
        id = "1",
        name = "Modern Sofa",
        description = "Comfortable 3-seater sofa",
        price = 599.99,
        image = R.drawable.corner_sofa,
        modelName = "corner_sofa",
        width = 220,
        height = 85,
        depth = 95,
        material = "Wood frame, Polyester fabric, Metal legs"
    ),
    Product(
        id = "2",
        name = "Coffee Table",
        description = "Wooden coffee table",
        price = 249.99,
        image = R.drawable.dining_table,
        modelName = "dining_table",
        width = 120,
        height = 45,
        depth = 60,
        material = "Solid oak, Glass top"
    ),
    Product(
        id = "3",
        name = "Floor Lamp",
        description = "Modern standing lamp",
        price = 129.99,
        image = R.drawable.double_bed,
        modelName = "double_bed",
        width = 45,
        height = 160,
        depth = 45,
        material = "Brushed steel, Fabric shade"
    ),
    Product(
        id = "4",
        name = "Dining Chair",
        description = "Elegant dining chair",
        price = 149.99,
        image = R.drawable.bauhaus_chair,
        modelName = "bauhaus_chair",
        width = 45,
        height = 95,
        depth = 55,
        material = "Walnut wood, Leather padding"
    )
)