package ru.spoonbill.droid.ui.profile.orders.orderslist.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.spoonbill.app.R
import ru.spoonbill.app.databinding.ProductReviewItemBinding
import ru.spoonbill.droid.ui.profile.orders.orderslist.models.TestCategoryModel

class ReviewProductAdapter: RecyclerView.Adapter<ReviewProductAdapter.ViewHolder>() {
    private var orderList = listOf(TestCategoryModel("Все товары"), TestCategoryModel("Мясо и птица"), TestCategoryModel("Рыба"), TestCategoryModel("Хлеб"))

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ProductReviewItemBinding.bind(view)
        fun bind(testCategoryModel: TestCategoryModel) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_review_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(orderList[position])
    }

    override fun getItemCount(): Int {
        return orderList.size
    }
}