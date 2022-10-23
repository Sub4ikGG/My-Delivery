package ru.spoonbill.droid.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import ru.spoonbill.droid.autoslider.SliderViewAdapter
import ru.spoonbill.app.databinding.ItemPromotionSliderViewBinding
import ru.spoonbill.droid.ui.home.entity.PromotionUi

class PromotionSliderAdapter(
    private val items: List<PromotionUi>
) : SliderViewAdapter<PromotionSliderAdapter.SliderViewHolder>() {

    inner class SliderViewHolder(private val binding: ItemPromotionSliderViewBinding) :
        SliderViewAdapter.ViewHolder(binding.root) {
        fun bind(item: PromotionUi) {
            Glide.with(binding.root.context)
                .load(item.uri)
                .centerCrop()
                .into(binding.sliderImage)
        }
    }

    override fun getCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup?): SliderViewHolder {
        return SliderViewHolder(
            ItemPromotionSliderViewBinding.inflate(
                LayoutInflater.from(parent?.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: SliderViewHolder?, position: Int) {
        viewHolder?.bind(items[position])
    }
}