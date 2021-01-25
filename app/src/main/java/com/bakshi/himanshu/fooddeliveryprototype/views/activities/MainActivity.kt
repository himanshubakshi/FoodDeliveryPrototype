package com.bakshi.himanshu.fooddeliveryprototype.views.activities

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bakshi.himanshu.fooddeliveryprototype.R
import com.bakshi.himanshu.fooddeliveryprototype.adapters.HeaderViewPagerAdapter
import com.bakshi.himanshu.fooddeliveryprototype.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // main layout binding
    lateinit var binding: ActivityMainBinding

    // Adapter for header view pager
    val headerViewPagerAdapter = HeaderViewPagerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViews()
    }

    private fun setupViews() {
        // Use full screen
        setNoLimitsFlag()

        // Setup view pager
        binding.headerViewPager.adapter = headerViewPagerAdapter
    }


    // Use entire screen space for rendering the activity, including status bar
    private fun setNoLimitsFlag() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
}