package ru.spoonbill.droid.ui.profile.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.spoonbill.app.databinding.BottomSheetSettingsBinding
import ru.spoonbill.droid.ui.profile.pressAnimated

class SettingsBottomSheet(private val value: String): BottomSheetDialogFragment() {

    private var _binding: BottomSheetSettingsBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetSettingsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.valueEditText.hint = value
        binding.valueConfirmButton.setOnClickListener {
            binding.valueConfirmButton.pressAnimated {
                Toast.makeText(requireContext(), "$value успешно сохранено!", Toast.LENGTH_SHORT).show()
                dismiss()
            }
        }
    }

}