package ru.spoonbill.droid.ui.profile.orders.orderslist.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.spoonbill.app.R
import ru.spoonbill.app.databinding.NewProductCardBinding
import ru.spoonbill.droid.ui.profile.orders.orderslist.models.TestOrderModel

class ProductsBoughtAdapter: RecyclerView.Adapter<ProductsBoughtAdapter.ViewHolder>() {
    private var orderList = listOf(TestOrderModel(), TestOrderModel(), TestOrderModel(), TestOrderModel(), TestOrderModel(), TestOrderModel(), TestOrderModel(), TestOrderModel(), TestOrderModel(), TestOrderModel())

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = NewProductCardBinding.bind(view)
        fun bind() {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.new_product_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return orderList.size
    }
}