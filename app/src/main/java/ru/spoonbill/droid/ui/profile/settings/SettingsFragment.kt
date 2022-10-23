package ru.spoonbill.droid.ui.profile.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import ru.spoonbill.app.R
import ru.spoonbill.app.databinding.FragmentSettingsBinding
import ru.spoonbill.droid.ui.profile.pressAnimated

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSettingsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.settingsToolbar.toolbarBackButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.settingsToolbar.toolbarTextView.text = "Настройки"

        var prompt = false
        var bottomSheet: SettingsBottomSheet
        binding.profileLogoutButton.setOnClickListener {
            binding.profileLogoutButton.pressAnimated {}
            if(prompt) {
                Toast.makeText(requireContext(), "Выход из аккаунта..", Toast.LENGTH_SHORT).show()
                requireActivity().finish()
                return@setOnClickListener
            }

            prompt = true
            Toast.makeText(requireContext(), "Нажмите ещё раз, если хотите выйти из аккаунта", Toast.LENGTH_SHORT).show()
        }

        binding.settingsNameButton.setOnClickListener {
            bottomSheet = SettingsBottomSheet("Имя")
            bottomSheet.show(parentFragmentManager, "SettingsBottom")
        }

        binding.settingsEmailButton.setOnClickListener {
            bottomSheet = SettingsBottomSheet("Почта")
            bottomSheet.show(parentFragmentManager, "SettingsBottom")
        }
    }

}