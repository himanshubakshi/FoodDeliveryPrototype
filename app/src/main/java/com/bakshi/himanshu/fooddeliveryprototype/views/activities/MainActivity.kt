package com.bakshi.himanshu.fooddeliveryprototype.views.activities

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bakshi.himanshu.fooddeliveryprototype.R
import com.bakshi.himanshu.fooddeliveryprototype.adapters.DishesViewPagerAdapter
import com.bakshi.himanshu.fooddeliveryprototype.adapters.WeeklyOffersViewPagerAdapter
import com.bakshi.himanshu.fooddeliveryprototype.databinding.ActivityMainBinding
import com.bakshi.himanshu.fooddeliveryprototype.viewmodels.DishesViewModel
import com.bakshi.himanshu.fooddeliveryprototype.viewmodels.WeeklyOffersViewModel
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.simpleName

    // main layout binding
    lateinit var binding: ActivityMainBinding

    // Adapter for header view pager
    val weeklyOffersViewPagerAdapter = WeeklyOffersViewPagerAdapter()
    val dishesTabAdapter = DishesViewPagerAdapter(this)

    // View Model
    private lateinit var weeklyOffersViewModel: WeeklyOffersViewModel
    private lateinit var dishesViewModel: DishesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        weeklyOffersViewModel = ViewModelProvider(this).get(WeeklyOffersViewModel::class.java)
        dishesViewModel = ViewModelProvider(this).get(DishesViewModel::class.java)
        setupViews()
    }

    private fun setupViews() {
        // Use full screen
        setNoLimitsFlag()

        // Setup offers header
        setupWeeklyOffers()

        setupDishesSection()
    }

    private fun setupWeeklyOffers() {
        // Setup view pager
        binding.headerViewPager.adapter = weeklyOffersViewPagerAdapter

        // fetch offers
        getOffers()
    }

    // Use entire screen space for rendering the activity, including status bar
    private fun setNoLimitsFlag() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    // fetch latest offers
    private fun getOffers() {
        weeklyOffersViewModel.getWeeklyOffers(this, false).observe(this, Observer { offers ->
            Log.d(TAG, "getOffers - weekly offers updated, offers: $offers")

            weeklyOffersViewPagerAdapter.update(offers)
        })
    }


    private fun setupDishesSection() {
        // Setup view pager
        binding.dishesViewPager.adapter = dishesTabAdapter

        // Connect viewPager and the tab layout
        TabLayoutMediator(binding.dishesTabLayout, binding.dishesViewPager, true) { tab, position ->
            tab.text = dishesTabAdapter.getDishTypeAtPosition(position)
        }.attach()

        // Fetch dishes
        dishesViewModel.let { dishesViewModel ->
            dishesViewModel.getDishes(this, true).observe(this, Observer {
                Log.d(TAG, "getDishes - dishes updated, dishes: $it")
                // update tabs
                dishesTabAdapter.update(dishesViewModel.getDishTypes(it))
            })
        }
    }
}