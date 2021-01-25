package com.bakshi.himanshu.fooddeliveryprototype.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bakshi.himanshu.fooddeliveryprototype.R
import com.bakshi.himanshu.fooddeliveryprototype.databinding.HeaderItemViewBinding

// Adapter for header view pager
class HeaderViewPagerAdapter : RecyclerView.Adapter<HeaderViewPagerAdapter.HeaderViewHolder>() {

    private val imagesArray = intArrayOf(
        R.drawable.header_banner,
        R.drawable.header_banner,
        R.drawable.header_banner
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val binding = DataBindingUtil.inflate<HeaderItemViewBinding>(
            LayoutInflater.from(parent.context), R.layout.header_item_view, parent, false
        )

        return HeaderViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(position, imagesArray[position])
    }

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int, imageDrawable: Int) {

            if (imageDrawable != 0) {
                getBinding()?.let { binding ->
                    binding.imageView.setImageDrawable(
                        AppCompatResources.getDrawable(itemView.context, imageDrawable)
                    )
                }
            }
        }

        private fun getBinding() = DataBindingUtil.getBinding<HeaderItemViewBinding>(this.itemView)
    }
}