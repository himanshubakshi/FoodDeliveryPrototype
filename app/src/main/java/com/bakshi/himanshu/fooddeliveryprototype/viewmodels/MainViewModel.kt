package com.bakshi.himanshu.fooddeliveryprototype.viewmodels

import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    var dishesViewModel: DishesViewModel = DishesViewModel()
    var weeklyOffersViewModel: WeeklyOffersViewModel = WeeklyOffersViewModel()

}