package ru.spoonbill.droid.ui.profile

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import ru.spoonbill.app.R
import ru.spoonbill.app.databinding.FragmentProfileBinding
import ru.spoonbill.droid.ui.fragments.BaseFragment

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileToolbar.toolbarTextView.text = "Мой профиль"
        binding.profileToolbar.toolbarBackButton.visibility = View.GONE

        binding.ordersButton.setOnClickListener {
            binding.ordersButton.pressAnimated {
                findNavController().navigate(R.id.action_myProfileFragment_to_ordersFragment)
            }
        }

        binding.addressesButton.setOnClickListener {
            binding.addressesButton.pressAnimated {
                findNavController().navigate(R.id.action_myProfileFragment_to_addressesFragment)
            }
        }

        binding.settingsButton.setOnClickListener {
            binding.settingsButton.pressAnimated {
                findNavController().navigate(R.id.action_myProfileFragment_to_settingsFragment)
            }
        }

        binding.supportButton.setOnClickListener {
            binding.supportButton.pressAnimated {
                openUrl(requireContext(), "https://t.me/sub4ikgg")
            }
        }
    }
}

fun View.pressAnimated(unit: () -> Unit) {
    this.animate().apply {
        duration = 150
        scaleX(0.95f)
        scaleY(0.95f)
    }.withEndAction {
        this.animate().apply {
            duration = 150
            scaleX(1f)
            scaleY(1f)
        }.withEndAction(unit)
    }
}

fun openUrl(context: Context, url: String) {
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    ContextCompat.startActivity(context, browserIntent, null)
}