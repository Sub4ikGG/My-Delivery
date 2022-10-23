package ru.spoonbill.droid.ui.profile.addresses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.spoonbill.app.databinding.FragmentAddressesBinding
import ru.spoonbill.droid.ui.fragments.BottomNavState
import ru.spoonbill.droid.ui.fragments.mainBottomNavState
import ru.spoonbill.droid.ui.profile.pressAnimated

class AddressesFragment : Fragment() {

    private var _binding: FragmentAddressesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddressesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val additionalAddressAdapter = AdditionalAddressAdapter()
        binding.additionalAddressesRcView.adapter = additionalAddressAdapter
        binding.additionalAddressesRcView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.addressesToolbar.toolbarTextView.text = "Адреса доставки"
        binding.addressesToolbar.toolbarBackButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.addAddressButton.setOnClickListener {
            binding.addAddressButton.pressAnimated {
                val bottomSheet = AddAddressBottomSheet()
                bottomSheet.show(parentFragmentManager, "AddAddressBottomSheet")
            }
        }

        binding.nestedScrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if(oldScrollY >= scrollY) mainBottomNavState.value = BottomNavState.EXPANDED
            else mainBottomNavState.value = BottomNavState.COLLAPSED
        }
    }
}