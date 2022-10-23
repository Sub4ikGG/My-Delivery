package ru.spoonbill.droid.ui.profile.addresses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.spoonbill.app.databinding.AddAddressBottomSheetBinding
import ru.spoonbill.app.databinding.FragmentSettingsBinding
import ru.spoonbill.droid.ui.profile.models.Address

class AddAddressBottomSheet: BottomSheetDialogFragment() {

    private var _binding: AddAddressBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = AddAddressBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    interface OnAddressInteractionListener {
        fun onAddressAdded(address: Address)
    }
}