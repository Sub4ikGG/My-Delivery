package ru.spoonbill.droid.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.spoonbill.app.R
import ru.spoonbill.app.databinding.FragmentSplashBinding
import ru.spoonbill.droid.ui.splash.SplashViewModel.UiEvent.*
import ru.spoonbill.app.utils.addRepeatedJob
import ru.spoonbill.app.utils.viewBinding
import ru.spoonbill.droid.common.extensions.nonNullCollect
import ru.spoonbill.droid.ui.fragments.BaseFragment

class SplashFragment : BaseFragment(R.layout.fragment_splash) {

    private val viewModel by viewModel<SplashViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectUiEventsFlow()
        viewModel.initialize()
    }

    private fun collectUiEventsFlow() {
        viewLifecycleOwner.addRepeatedJob(Lifecycle.State.STARTED) {
            viewModel.uiEventsFlow.nonNullCollect { event ->
                when (event) {
                    is SignedInEvent -> viewModel.fetchData()
                    is NotSignedInEvent -> navigateTo(R.id.action_splashFragment_to_signInFragment)
                    is CartExistsEvent -> navigateToMainScreen()
                    is CartNotExistsEvent -> viewModel.createCart()
                    is CartCreationSuccessEvent -> navigateToMainScreen()
                    is CartCreationFailedEvent -> showErrorMessage(event.e.message)
                    is FetchingSuccessEvent -> navigateToMainScreen()//navigateTo(R.id.action_splashFragment_to_signInFragment)
                    is FetchingFailureEvent -> showErrorMessageAndFinish("Something went wrong")
                }
            }
        }
    }

    private fun navigateToMainScreen() {
        navigateTo(R.id.action_splashFragment_to_mainNavHostFragment)
    }

}