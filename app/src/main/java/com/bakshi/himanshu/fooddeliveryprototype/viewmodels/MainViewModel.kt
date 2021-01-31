package com.bakshi.himanshu.fooddeliveryprototype.viewmodels

import androidx.lifecycle.ViewModel
import com.bakshi.himanshu.fooddeliveryprototype.data.models.Dish

class MainViewModel : ViewModel() {

    var dishesViewModel: DishesViewModel = DishesViewModel()
    var weeklyOffersViewModel: WeeklyOffersViewModel = WeeklyOffersViewModel()
    var cartViewModel: CartViewModel = CartViewModel()

    fun addToCart(dish: Dish) = cartViewModel.addToCart(dish)
}