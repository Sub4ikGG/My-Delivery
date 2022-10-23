package ru.spoonbill.droid.ui.profile.orders

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import ru.spoonbill.app.R
import ru.spoonbill.app.databinding.FragmentOrdersBinding
import ru.spoonbill.droid.ui.fragments.BottomNavState
import ru.spoonbill.droid.ui.fragments.mainBottomNavState

class OrdersFragment : Fragment() {

    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding!!

    private var firstOpened = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOrdersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        val navController = binding.ordersFragmentContainer.findNavController()

        binding.ordersToolbar.toolbarBackButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.productsBoughtButton.setOnClickListener {
            if(firstOpened) {
                firstOpened = false

                binding.ordersListButton.setBackgroundResource(R.drawable.order_tab_button_first_deactivated)
                binding.ordersListTextView.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.primary
                    )
                )
                binding.productsBoughtButton.setBackgroundResource(R.drawable.order_tab_button_second_activated)
                binding.productsBoughtTextView.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                )

                navController.navigate(R.id.action_orderListFragment_to_productsBoughtFragment)
            }
        }

        binding.ordersListButton.setOnClickListener {
            if(!firstOpened) {
                firstOpened = true
                binding.ordersListButton.setBackgroundResource(R.drawable.order_tab_button_first_activated)
                binding.ordersListTextView.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                )
                binding.productsBoughtButton.setBackgroundResource(R.drawable.order_tab_button_second_deactivated)
                binding.productsBoughtTextView.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.primary
                    )
                )

                navController.popBackStack()
            }
        }

        requireView().isFocusableInTouchMode = true
        requireView().requestFocus()
        requireView().setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {

                findNavController().popBackStack()
                true
            } else false
        }
    }
}