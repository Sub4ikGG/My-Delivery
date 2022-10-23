package ru.spoonbill.droid.ui.sign_in

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.spoonbill.app.R
import ru.spoonbill.app.databinding.FragmentSignInBinding
import ru.spoonbill.app.utils.addRepeatedJob
import ru.spoonbill.app.utils.handleButtonState
import ru.spoonbill.app.utils.showSoftInputKeyboard
import ru.spoonbill.app.utils.viewBinding
import ru.spoonbill.droid.ui.fragments.BaseFragment
import ru.spoonbill.droid.ui.sign_in.SignInViewModel.UiEvent.*

class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {

    private val binding by viewBinding(FragmentSignInBinding::bind)
    private val viewModel by viewModel<SignInViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews()
        collectUiEventsFlow()
    }

    private fun setupViews() {
        binding.buttonSendCode.isEnabled = true
        binding.tilPhoneNumber.editText?.apply {
            showSoftInputKeyboard()
            //handleButtonState(binding.buttonSendCode, 10)
        }
        binding.buttonSendCode.setOnClickListener {
            /*binding.tilPhoneNumber.also {
                val phoneNumber = it.prefixText?.toString() + it.editText?.text?.toString()
                viewModel.sendCodeEvent(phoneNumber)
            }*/

            val phoneNumber = binding.tilPhoneNumber.prefixText?.toString() + binding.tilPhoneNumber.editText?.text?.toString()

            findNavController().navigate(R.id.action_signInFragment_to_mainNavHostFragment)
            CoroutineScope(Dispatchers.IO).launch {
                //findNavController().navigate(R.id.action_signInFragment_to_mainNavHostFragment)
                /*viewModel.sendCodeEvent(phoneNumber)*/
            }
            //navigateToPhoneVerification(phone = phoneNumber)
        }

    }

    private fun collectUiEventsFlow() {
        viewLifecycleOwner.addRepeatedJob(Lifecycle.State.STARTED) {
            viewModel.uiEventsFlow.collectLatest { event ->
                when (event) {
                    is SendCodeEvent -> viewModel.createQueue(event.phone)
                    is SendCodeFailureEvent -> showErrorMessage(event.message)
                    is SendCodeSuccessEvent -> navigateToPhoneVerification(event.phone)
                }

                println("event: $event")
            }
        }
    }

    private fun navigateToPhoneVerification(phone: String) {
        val action = SignInFragmentDirections.actionSignInFragmentToPhoneVerificationFragment(phone)
        findNavController().navigate(action)
    }
}