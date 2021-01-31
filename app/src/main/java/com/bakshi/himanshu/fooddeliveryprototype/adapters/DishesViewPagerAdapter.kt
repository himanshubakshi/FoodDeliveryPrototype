package com.bakshi.himanshu.fooddeliveryprototype.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bakshi.himanshu.fooddeliveryprototype.views.fragments.DishesListFragment

class DishesViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val dishes: MutableList<String> = mutableListOf()

    fun update(newDishes: List<String>) {
        dishes.apply {
            clear()
            addAll(newDishes)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dishes.size
    }

    override fun createFragment(position: Int): Fragment {
        return DishesListFragment.newInstance(dishes[position])
    }

    fun getDishTypeAtPosition(position: Int): String? {
        if (position >= 0 && position < dishes.size) {
            return dishes[position]
        }

        return null
    }
}