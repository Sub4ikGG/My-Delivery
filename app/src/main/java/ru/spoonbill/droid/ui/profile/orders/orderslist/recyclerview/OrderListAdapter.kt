package ru.spoonbill.droid.ui.profile.orders.orderslist.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.spoonbill.app.R
import ru.spoonbill.app.databinding.OrderListItemBinding
import ru.spoonbill.droid.ui.profile.orders.orderslist.models.TestOrderModel


class OrderListAdapter: RecyclerView.Adapter<OrderListAdapter.ViewHolder>() {
    private var orderList = listOf(TestOrderModel(), TestOrderModel(), TestOrderModel(), TestOrderModel(), TestOrderModel())

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = OrderListItemBinding.bind(view)
        fun bind() {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return orderList.size
    }
}