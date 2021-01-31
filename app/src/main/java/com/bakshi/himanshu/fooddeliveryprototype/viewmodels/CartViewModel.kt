package com.bakshi.himanshu.fooddeliveryprototype.viewmodels

import androidx.lifecycle.ViewModel
import com.bakshi.himanshu.fooddeliveryprototype.data.models.Dish

class CartViewModel : ViewModel() {
    private val cartMap: MutableMap<Dish, Int> = mutableMapOf()

    fun addToCart(dish: Dish) {
        if (cartMap.containsKey(dish)) {
            cartMap[dish]?.plus(1)
        } else {
            cartMap[dish] = dish.defaultCartQuantity
        }
    }
}