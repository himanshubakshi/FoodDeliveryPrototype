package com.bakshi.himanshu.fooddeliveryprototype.views.activities

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bakshi.himanshu.fooddeliveryprototype.R
import com.bakshi.himanshu.fooddeliveryprototype.databinding.ActivityMainBinding
import com.bakshi.himanshu.fooddeliveryprototype.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.simpleName

    // main layout binding
    lateinit var binding: ActivityMainBinding

    // View Model
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setNoLimitsFlag()
    }

    // Use entire screen space for rendering the activity, including status bar
    private fun setNoLimitsFlag() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

}
