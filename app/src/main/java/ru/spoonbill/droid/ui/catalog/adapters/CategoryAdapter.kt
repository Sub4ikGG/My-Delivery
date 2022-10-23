package ru.spoonbill.droid.ui.catalog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.spoonbill.app.databinding.ItemCategoryBinding
import ru.spoonbill.droid.ui.catalog.model.ChildCategoryUi
import ru.spoonbill.app.utils.OnCategoryItemClick

class CategoryAdapter(
    private val categoryItems: List<ChildCategoryUi>,
    private val onCategoryItemClick: (ChildCategoryUi) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChildCategoryUi) {
            binding.textName.text = item.name
            binding.root.setOnClickListener { onCategoryItemClick(item) }
            Glide.with(binding.root.context)
                //.load(item.imageUrl)
                .load("https://img.vkusvill.ru/pim/images/site_LargeWebP/990fda4e-40a1-4bf2-a459-3e86411dbf7d.png?1652776284.6079")
                .centerCrop()
                .into(binding.imageCategory)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(categoryItems[position])
    }

    override fun getItemCount(): Int = categoryItems.size
}