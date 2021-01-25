package com.bakshi.himanshu.fooddeliveryprototype.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.bakshi.himanshu.fooddeliveryprototype.data.repository.WeeklyOffersRepository
import com.bakshi.himanshu.fooddeliveryprototype.data.service.WeeklyOffersService

class WeeklyOffersViewModel : ViewModel() {
    private val weeklyOffersRepository = WeeklyOffersRepository(WeeklyOffersService.getService())

    // Returns a weekly offers LiveData object
    fun getWeeklyOffers(context: Context, useMocks: Boolean = false) =
        weeklyOffersRepository.getWeeklyOffers(context, useMocks)

    override fun onCleared() {
        super.onCleared()
        weeklyOffersRepository.clear()
    }
}