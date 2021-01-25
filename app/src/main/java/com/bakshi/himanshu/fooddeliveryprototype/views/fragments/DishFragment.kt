package com.bakshi.himanshu.fooddeliveryprototype.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bakshi.himanshu.fooddeliveryprototype.R
import com.bakshi.himanshu.fooddeliveryprototype.databinding.FragmentDishBinding
import com.bakshi.himanshu.fooddeliveryprototype.utils.Constants

class DishFragment : Fragment() {
    private var dishType: String? = null

    lateinit var binding: FragmentDishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dishType = it.getString(Constants.DISH_TYPE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dish, container, false)

        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.textView.text = dishType
    }

    companion object {
      
        @JvmStatic
        fun newInstance(dishType: String) =
            DishFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.DISH_TYPE, dishType)
                }
            }
    }
}