package com.bakshi.himanshu.fooddeliveryprototype.data.models

data class Dish(
    val name: String?,
    val id: String?,
    val type: String?,
    val imageUrl: String?,
    val weight: GenericValueClass?,
    val size: GenericValueClass?,
    val cost: Cost?,
    val minimumCartQuantity: Int,
    val ingredients: List<Ingredient>?
) {
    val priceLabel
        get() = "${cost?.value} ${cost?.unit}"

    val ingredientsLabel: String
        get() {
            var result = ""
            ingredients?.forEach {
                result += "${it.name}, "
            }

            return result.trimEnd(' ', ',')
        }
}


data class GenericValueClass(
    val value: Int,
    val unit: String?
)

data class Cost(
    val value: Float,
    val unit: String?
)

data class Ingredient(
    val name: String?,
    val id: String?,
    val type: String?,
    val isSpicy: Boolean = false,
    val isNonVeg: Boolean = false
)
