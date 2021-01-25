package com.bakshi.himanshu.fooddeliveryprototype.views.activities

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bakshi.himanshu.fooddeliveryprototype.R
import com.bakshi.himanshu.fooddeliveryprototype.adapters.HeaderViewPagerAdapter
import com.bakshi.himanshu.fooddeliveryprototype.databinding.ActivityMainBinding
import com.bakshi.himanshu.fooddeliveryprototype.viewmodels.WeeklyOffersViewModel

class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.simpleName

    // main layout binding
    lateinit var binding: ActivityMainBinding

    // Adapter for header view pager
    val weeklyOffersViewPagerAdapter = HeaderViewPagerAdapter()

    // View Model
    private lateinit var weeklyOffersViewModel: WeeklyOffersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        weeklyOffersViewModel = ViewModelProvider(this).get(WeeklyOffersViewModel::class.java)
        setupViews()
    }

    private fun setupViews() {
        // Use full screen
        setNoLimitsFlag()

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
        weeklyOffersViewModel.getWeeklyOffers(this).observe(this, Observer {
            Log.d(TAG, "getOffers - weekly offers updated, offers: ${it.toString()}")

            // TODO: hook up view updates
        })
    }
}