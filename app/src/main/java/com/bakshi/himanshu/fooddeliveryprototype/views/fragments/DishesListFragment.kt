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
import androidx.recyclerview.widget.LinearLayoutManager
import com.bakshi.himanshu.fooddeliveryprototype.R
import com.bakshi.himanshu.fooddeliveryprototype.adapters.DishesRecyclerViewAdapter
import com.bakshi.himanshu.fooddeliveryprototype.databinding.FragmentDishesListBinding
import com.bakshi.himanshu.fooddeliveryprototype.utils.Constants
import com.bakshi.himanshu.fooddeliveryprototype.viewmodels.DishesViewModel

// Fragment which shows a list dishes for the current selected tab
class DishesListFragment : Fragment() {
    private val TAG = DishesListFragment::class.java.simpleName

    private var dishType: String? = null

    lateinit var binding: FragmentDishesListBinding
    private var dishesRecyclerViewAdapter = DishesRecyclerViewAdapter()
    private var dishesViewModel: DishesViewModel? = null

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dishes_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dishesViewModel = activity?.let { ViewModelProvider(it).get(DishesViewModel::class.java) }
        initViews()
    }

    private fun initViews() {
        binding.dishesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dishesRecyclerViewAdapter
        }

        dishesViewModel?.let { dishesViewModel ->
            context?.let { context ->
                dishesViewModel.getDishes(context, true).observe(this, Observer { dishesList ->
                    Log.d(TAG, "getDishes - dishes updated, dishes: $dishesList")
                    // update tabs
                    dishesRecyclerViewAdapter.update(dishesList.filter { it.type == dishType })
                })
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(dishType: String) =
            DishesListFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.DISH_TYPE, dishType)
                }
            }
    }
}