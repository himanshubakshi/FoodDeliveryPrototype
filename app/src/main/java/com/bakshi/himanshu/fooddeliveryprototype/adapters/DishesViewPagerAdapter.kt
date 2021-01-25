package com.bakshi.himanshu.fooddeliveryprototype.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bakshi.himanshu.fooddeliveryprototype.views.fragments.DishFragment

class DishesViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

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
        return DishFragment.newInstance(dishes[position])
    }

    fun getDishTypeAtPosition(position: Int): String? {
        if (position >= 0 && position < dishes.size) {
            return dishes[position]
        }

        return null
    }
}