package ru.spoonbill.droid.ui.profile.addresses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.spoonbill.app.R

class AdditionalAddressAdapter: RecyclerView.Adapter<AdditionalAddressAdapter.ViewHolder>() {
    private var addresses = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8)

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind() {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdditionalAddressAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.additional_address_item, parent, false)
        return AdditionalAddressAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdditionalAddressAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return addresses.size
    }
}