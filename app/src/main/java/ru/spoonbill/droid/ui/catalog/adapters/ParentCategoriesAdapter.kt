package ru.spoonbill.droid.ui.catalog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.spoonbill.app.common.GridSpacingItemDecoration
import ru.spoonbill.app.databinding.ItemCategoryParentBinding
import ru.spoonbill.droid.ui.catalog.model.ChildCategoryUi
import ru.spoonbill.droid.ui.catalog.model.ParentCategoryUi

class ParentCategoriesAdapter(
    private val mSpacing: Int,
    private val onCategoryItemClick: (ChildCategoryUi) -> Unit
) : ListAdapter<ParentCategoryUi, ParentCategoriesAdapter.ItemViewHolder>(PARENT_CATEGORIES_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemCategoryParentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder(private val binding: ItemCategoryParentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ParentCategoryUi){
            binding.textHeader.text = item.name
            setupChildCategoriesRecyclerView(item)
        }

        private fun setupChildCategoriesRecyclerView(item: ParentCategoryUi) = with(binding.rvCatalogs) {
            adapter = CategoryAdapter(item.childCategories, onCategoryItemClick)
            layoutManager = GridLayoutManager(binding.root.context, 2)
            isNestedScrollingEnabled = false
            setHasFixedSize(true)
            addItemDecoration(GridSpacingItemDecoration(2, mSpacing, false))
        }
    }

    companion object {
        val PARENT_CATEGORIES_COMPARATOR = object : DiffUtil.ItemCallback<ParentCategoryUi>() {
            override fun areItemsTheSame(oldItem: ParentCategoryUi, newItem: ParentCategoryUi): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ParentCategoryUi, newItem: ParentCategoryUi): Boolean =
                oldItem == newItem
        }
    }
}