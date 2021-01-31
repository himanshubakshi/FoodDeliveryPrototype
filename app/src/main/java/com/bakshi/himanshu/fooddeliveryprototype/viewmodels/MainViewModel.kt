package com.bakshi.himanshu.fooddeliveryprototype.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.bakshi.himanshu.fooddeliveryprototype.data.models.Dish

class MainViewModel : ViewModel() {

    // Toggle this variable to use mocks or fetch from network
    var useMocks = false

    var dishesViewModel: DishesViewModel = DishesViewModel()
    var weeklyOffersViewModel: WeeklyOffersViewModel = WeeklyOffersViewModel()
    var cartViewModel: CartViewModel = CartViewModel()

    fun getWeeklyOffers(context: Context) = weeklyOffersViewModel.getWeeklyOffers(context, useMocks)
    fun getDishes(context: Context) = dishesViewModel.getDishes(context, useMocks)

    fun addToCart(dish: Dish) = cartViewModel.addToCart(dish)
}