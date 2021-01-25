package com.bakshi.himanshu.fooddeliveryprototype.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bakshi.himanshu.fooddeliveryprototype.R
import com.bakshi.himanshu.fooddeliveryprototype.data.models.WeeklyOffer
import com.bakshi.himanshu.fooddeliveryprototype.databinding.HeaderItemViewBinding
import com.bumptech.glide.Glide

// Adapter for header view pager
class WeeklyOffersViewPagerAdapter :
    RecyclerView.Adapter<WeeklyOffersViewPagerAdapter.HeaderViewHolder>() {

    private val weeklyOffers: MutableList<WeeklyOffer> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val binding = DataBindingUtil.inflate<HeaderItemViewBinding>(
            LayoutInflater.from(parent.context), R.layout.header_item_view, parent, false
        )

        return HeaderViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return weeklyOffers.size
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(weeklyOffers[position])
    }

    fun update(offers: List<WeeklyOffer>) {
        weeklyOffers.apply {
            clear()
            addAll(offers)
        }
        notifyDataSetChanged()
    }

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(weeklyOffer: WeeklyOffer) {

            getBinding()?.let { binding ->
                weeklyOffer.url?.let { imageUrl ->
                    AppCompatResources.getDrawable(
                        itemView.context,
                        R.drawable.weekly_offer_error_banner
                    )?.let { errorDrawable ->
                        Glide.with(itemView.context).load(imageUrl)
                            .error(errorDrawable)
                            .placeholder(errorDrawable)
                            .into(binding.imageView)
                    }
                }
            }
        }

        private fun getBinding() = DataBindingUtil.getBinding<HeaderItemViewBinding>(this.itemView)
    }
}