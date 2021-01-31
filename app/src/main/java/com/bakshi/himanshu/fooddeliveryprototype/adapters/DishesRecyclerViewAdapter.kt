package com.bakshi.himanshu.fooddeliveryprototype.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bakshi.himanshu.fooddeliveryprototype.R
import com.bakshi.himanshu.fooddeliveryprototype.data.models.Dish
import com.bakshi.himanshu.fooddeliveryprototype.databinding.ItemDishBinding
import com.bakshi.himanshu.fooddeliveryprototype.utils.Constants
import com.bakshi.himanshu.fooddeliveryprototype.viewmodels.MainViewModel
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DishesRecyclerViewAdapter(private val mainViewModel: MainViewModel) :
    RecyclerView.Adapter<DishesRecyclerViewAdapter.DishViewHolder>() {

    private val dishes: MutableList<Dish> = mutableListOf()

    fun update(newDishes: List<Dish>) {
        dishes.apply {
            clear()
            addAll(newDishes)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val binding: ItemDishBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_dish, parent, false
        )
        return DishViewHolder(binding.root, mainViewModel)
    }

    override fun getItemCount(): Int {
        return dishes.size
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        holder.bind(dishes[position])
    }

    class DishViewHolder(view: View, private val mainViewModel: MainViewModel) :
        RecyclerView.ViewHolder(view) {
        private fun getBinding() = DataBindingUtil.getBinding<ItemDishBinding>(this.itemView)

        fun bind(dish: Dish) {
            getBinding()?.let { binding ->

                binding.name.text = dish.name
                Glide.with(itemView.context).load(dish.imageUrl)
                    .error(R.drawable.weekly_offer_error_banner)
                    .placeholder(R.drawable.weekly_offer_error_banner)
                    .into(binding.dishLogo)

                updateCartButton(
                    binding.addToCart,
                    dish.priceLabel(),
                    R.drawable.add_to_cart_default
                )
                binding.addToCart.setOnClickListener {
                    mainViewModel.addToCart(dish)

                    // Add to cart animation
                    animateCartButton(dish)
                }
            }
        }

        private fun updateCartButton(
            button: Button?, label: String, backgroundDrawableRes: Int
        ) {
            button?.apply {
                text = label
                background =
                    ContextCompat.getDrawable(
                        itemView.context,
                        backgroundDrawableRes
                    )
            }

        }

        private fun animateCartButton(dish: Dish) {

            getBinding()?.let {
                // update text and color
                updateCartButton(
                    it.addToCart,
                    Constants.ADDED_TO_CART_FORMAT.format(dish.minimumCartQuantity),
                    R.drawable.add_to_cart_animation
                )

                // Reset text and color
                CoroutineScope(Dispatchers.Main).launch {
                    // Reset button color
                    delay(1000)
                    updateCartButton(
                        it.addToCart,
                        dish.priceLabel(),
                        R.drawable.add_to_cart_default
                    )

                }
            }
        }
    }
}