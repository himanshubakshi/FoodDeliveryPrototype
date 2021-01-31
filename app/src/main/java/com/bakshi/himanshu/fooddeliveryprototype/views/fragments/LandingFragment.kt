package com.bakshi.himanshu.fooddeliveryprototype.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bakshi.himanshu.fooddeliveryprototype.R
import com.bakshi.himanshu.fooddeliveryprototype.adapters.DishesViewPagerAdapter
import com.bakshi.himanshu.fooddeliveryprototype.adapters.WeeklyOffersViewPagerAdapter
import com.bakshi.himanshu.fooddeliveryprototype.databinding.LandingFragmentBinding
import com.bakshi.himanshu.fooddeliveryprototype.interfaces.OnFragmentInteractionListener
import com.bakshi.himanshu.fooddeliveryprototype.viewmodels.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator

class LandingFragment : Fragment() {

    val TAG = LandingFragment::class.java.simpleName

    // Data Binding
    lateinit var binding: LandingFragmentBinding

    private val onFragmentInteractionListener: OnFragmentInteractionListener?
        get() = activity as? OnFragmentInteractionListener

    // Adapter for header view pager
    private val weeklyOffersViewPagerAdapter = WeeklyOffersViewPagerAdapter()
    private var dishesViewPagerAdapter: DishesViewPagerAdapter? = null

    // ViewModel
    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.landing_fragment, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        // Setup offers header
        setupWeeklyOffers()

        // Setup dishes section
        setupDishesSection()

        // Setup card button
        setupCart()
    }

    private fun setupWeeklyOffers() {
        // Setup view pagers
        dishesViewPagerAdapter = DishesViewPagerAdapter(this)
        binding.dishesViewPager.adapter = dishesViewPagerAdapter

        binding.offersViewPager.adapter = weeklyOffersViewPagerAdapter

        TabLayoutMediator(binding.offersTabLayout, binding.offersViewPager, true) { tab, position ->
        }.attach()

        // fetch offers
        getOffers()
    }


    // fetch latest offers
    private fun getOffers() {
        context?.let {
            mainViewModel.weeklyOffersViewModel.getWeeklyOffers(it, true)
                .observe(viewLifecycleOwner, Observer { offers ->
                    Log.d(TAG, "getOffers - weekly offers updated, offers: $offers")

                    weeklyOffersViewPagerAdapter.update(offers)
                })
        }
    }

    private fun setupDishesSection() {
        // Setup view pager
        binding.dishesViewPager.adapter = dishesViewPagerAdapter

        // Connect viewPager and the tab layout
        TabLayoutMediator(binding.dishesTabLayout, binding.dishesViewPager, true) { tab, position ->
            tab.text = dishesViewPagerAdapter?.getDishTypeAtPosition(position)
        }.attach()

        // Fetch dishes
        context?.let {
            mainViewModel.dishesViewModel.getDishes(it, true).observe(viewLifecycleOwner, Observer {
                Log.d(TAG, "getDishes - dishes updated, dishes: $it")
                // update tabs
                dishesViewPagerAdapter?.update(mainViewModel.dishesViewModel.getDishTypes(it))
            })
        }
    }

    private fun setupCart() {
        binding.cart.setOnClickListener {
            onFragmentInteractionListener?.goToCart()
        }
    }
}