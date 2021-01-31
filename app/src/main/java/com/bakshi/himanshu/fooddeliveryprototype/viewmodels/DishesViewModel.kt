package com.bakshi.himanshu.fooddeliveryprototype.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.bakshi.himanshu.fooddeliveryprototype.data.models.Dish
import com.bakshi.himanshu.fooddeliveryprototype.data.repository.DishesRepository
import com.bakshi.himanshu.fooddeliveryprototype.data.service.DataService

class DishesViewModel : ViewModel() {
    private val dishesRepository = DishesRepository(DataService.getService())

    // Returns a dishes LiveData object
    fun getDishes(context: Context, useMocks: Boolean = false) =
        dishesRepository.getDishes(context, useMocks)

    // TOOD: what should be the best way to show a list of dish types in the viewPager?
    // Should we decouple the types to be shown in a dishes view pager from the list of all available types
    // We could use an external/local config which is predefined, to show the tabs
    // This data could flow from an API as well

    // Get the list of different types of dishes available
    fun getDishTypes(dishes: List<Dish>): List<String> {
        val dishesSet: HashSet<String> = hashSetOf()
        dishes.forEach { dish ->
            dish.type?.let { type -> dishesSet.add(type) }
        }
        return dishesSet.toList()
    }

    override fun onCleared() {
        super.onCleared()
        dishesRepository.clear()
    }
}