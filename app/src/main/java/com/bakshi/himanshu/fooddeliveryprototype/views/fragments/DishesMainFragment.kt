package com.bakshi.himanshu.fooddeliveryprototype.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bakshi.himanshu.fooddeliveryprototype.R
import com.bakshi.himanshu.fooddeliveryprototype.adapters.DishesViewPagerAdapter
import com.bakshi.himanshu.fooddeliveryprototype.databinding.DishesMainFragmentBinding
import com.bakshi.himanshu.fooddeliveryprototype.viewmodels.DishesViewModel
import com.google.android.material.tabs.TabLayoutMediator

// Root fragment to host the viewpager and the tab view
class DishesMainFragment : Fragment() {
    val TAG = DishesMainFragment::class.java.simpleName

    lateinit var binding: DishesMainFragmentBinding
    var dishesTabAdapter: DishesViewPagerAdapter? = null
    private var dishesViewModel: DishesViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.dishes_main_fragment, container, false)
        dishesViewModel = activity?.let { ViewModelProvider(it).get(DishesViewModel::class.java) }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDishesSection()
    }

    private fun setupDishesSection() {
        // Setup view pager
        var dishesTabAdapter: DishesViewPagerAdapter? = null
        dishesTabAdapter = DishesViewPagerAdapter(this.requireActivity())
        binding.dishesViewPager.adapter = dishesTabAdapter

        // Connect viewPager and the tab layout
        TabLayoutMediator(binding.dishesTabLayout, binding.dishesViewPager, true) { tab, position ->
            tab.text = dishesTabAdapter.getDishTypeAtPosition(position)
        }.attach()

        // Fetch dishes
        context?.let {
            dishesViewModel?.let { dishesViewModel ->
                dishesViewModel.getDishes(it, true).observe(this, Observer {
                    Log.d(TAG, "getDishes - dishes updated, dishes: $it")
                    // update tabs
                    dishesTabAdapter.update(dishesViewModel.getDishTypes(it))
                })
            }
        }
    }
}