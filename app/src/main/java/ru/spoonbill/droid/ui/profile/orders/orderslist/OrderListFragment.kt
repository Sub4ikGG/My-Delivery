package ru.spoonbill.droid.ui.profile.orders.orderslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ru.spoonbill.app.databinding.FragmentOrderListBinding
import ru.spoonbill.droid.ui.fragments.BottomNavState
import ru.spoonbill.droid.ui.fragments.mainBottomNavState
import ru.spoonbill.droid.ui.profile.orders.orderslist.recyclerview.OrderListAdapter
import ru.spoonbill.droid.ui.profile.orders.orderslist.recyclerview.ReviewProductAdapter

class OrderListFragment : Fragment() {

    private var _binding: FragmentOrderListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOrderListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val orderListAdapter = OrderListAdapter()
        val reviewProductAdapter = ReviewProductAdapter()

        binding.ordersRcView.adapter = orderListAdapter
        binding.ordersRcView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.productsReviewRcView.adapter = reviewProductAdapter
        binding.productsReviewRcView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.nestedScrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if(oldScrollY >= scrollY) mainBottomNavState.value = BottomNavState.EXPANDED
            else mainBottomNavState.value = BottomNavState.COLLAPSED
        }
    }

    override fun onStop() {
        super.onStop()

        mainBottomNavState.value = BottomNavState.EXPANDED
    }
}