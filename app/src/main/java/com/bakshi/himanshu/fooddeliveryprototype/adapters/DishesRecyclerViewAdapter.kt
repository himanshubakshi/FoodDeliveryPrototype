package com.bakshi.himanshu.fooddeliveryprototype.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bakshi.himanshu.fooddeliveryprototype.R
import com.bakshi.himanshu.fooddeliveryprototype.data.models.Dish
import com.bakshi.himanshu.fooddeliveryprototype.databinding.ItemDishBinding
import com.bumptech.glide.Glide

class DishesRecyclerViewAdapter : RecyclerView.Adapter<DishesRecyclerViewAdapter.DishViewHolder>() {

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
        return DishViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return dishes.size
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        holder.bind(dishes[position])
    }

    class DishViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private fun getBinding() = DataBindingUtil.getBinding<ItemDishBinding>(this.itemView)

        fun bind(dish: Dish) {
            getBinding()?.let { binding ->

                binding.name.text = dish.name
                Glide.with(itemView.context).load(dish.imageUrl)
                    .error(R.drawable.weekly_offer_error_banner)
                    .placeholder(R.drawable.weekly_offer_error_banner)
                    .into(binding.dishLogo)
            }
        }
    }
}