package com.bakshi.himanshu.fooddeliveryprototype.interfaces

import com.bakshi.himanshu.fooddeliveryprototype.data.models.Dish

interface OnFragmentInteractionListener {
    fun goToCart()
    fun addToCart(dish: Dish)
}