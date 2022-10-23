package ru.spoonbill.droid.ui.profile.orders.orderslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import ru.spoonbill.app.databinding.FragmentProductsBoughtBinding
import ru.spoonbill.droid.ui.fragments.BottomNavState
import ru.spoonbill.droid.ui.fragments.mainBottomNavState
import ru.spoonbill.droid.ui.profile.orders.orderslist.recyclerview.ProductsBoughtAdapter
import ru.spoonbill.droid.ui.profile.orders.orderslist.recyclerview.ProductsBoughtCategoryAdapter

class ProductsBoughtFragment: Fragment() {

    private var _binding: FragmentProductsBoughtBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProductsBoughtBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productsBoughtAdapter = ProductsBoughtAdapter()
        val productsBoughtCategoryAdapter = ProductsBoughtCategoryAdapter()

        binding.productsBoughtRcView.adapter = productsBoughtAdapter
        binding.productsBoughtRcView.layoutManager = GridLayoutManager(requireContext(), 2)

        binding.productsBoughtCategoriesRcView.adapter = productsBoughtCategoryAdapter
        binding.productsBoughtCategoriesRcView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

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