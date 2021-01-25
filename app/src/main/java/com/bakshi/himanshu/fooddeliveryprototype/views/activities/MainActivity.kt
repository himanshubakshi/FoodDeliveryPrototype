package com.bakshi.himanshu.fooddeliveryprototype.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bakshi.himanshu.fooddeliveryprototype.R
import com.bakshi.himanshu.fooddeliveryprototype.adapters.HeaderViewPagerAdapter
import com.bakshi.himanshu.fooddeliveryprototype.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val headerViewPagerAdapter = HeaderViewPagerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViews()
    }

    private fun setupViews() {
        binding.headerViewPager.adapter = headerViewPagerAdapter
    }
}