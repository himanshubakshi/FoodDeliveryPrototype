package com.bakshi.himanshu.fooddeliveryprototype.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bakshi.himanshu.fooddeliveryprototype.R
import com.bakshi.himanshu.fooddeliveryprototype.databinding.CartFragmentBinding
import com.bakshi.himanshu.fooddeliveryprototype.viewmodels.MainViewModel

class CartFragment : Fragment() {
    // Data Binding
    private lateinit var binding: CartFragmentBinding

    // ViewModel
    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.cart_fragment, container, false)
        return binding.root
    }
}