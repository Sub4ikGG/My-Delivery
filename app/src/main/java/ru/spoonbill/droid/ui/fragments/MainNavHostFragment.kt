package ru.spoonbill.droid.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import ru.spoonbill.app.R
import ru.spoonbill.app.databinding.FragmentMainNavHostBinding
import ru.spoonbill.droid.ui.cart.CartBottomSheetFragment

enum class BottomNavState {
    EXPANDED,
    COLLAPSED
}

val mainBottomNavState: MutableStateFlow<BottomNavState> = MutableStateFlow(BottomNavState.EXPANDED)

class MainNavHostFragment : Fragment() {
    private lateinit var binding: FragmentMainNavHostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        collectMainBottomNavState()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainNavHostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupBottomNavBar()
        setupViews()
    }

    private fun setupViews() {
        binding.fab.setOnClickListener { CartBottomSheetFragment.show(parentFragmentManager) }
    }

    private fun setupBottomNavBar() {
        val navHost =
            childFragmentManager.findFragmentById(R.id.home_nav_host_container) as NavHostFragment
        binding.bottomNavBar.setupWithNavController(navHost.navController)
    }

    private fun collectMainBottomNavState() {
        lifecycleScope.launchWhenStarted {
            mainBottomNavState.collect { state ->
                when(state) {
                    BottomNavState.COLLAPSED -> showBottomNav()
                    BottomNavState.EXPANDED -> hideBottomNav()
                }
            }
        }
    }

    private fun showBottomNav() {
        binding.bottomAppBar.animate().apply {
            duration = 1200
            translationY(1000f)
        }

        binding.fab.animate().apply {
            duration = 1200
            translationY(1000f)
        }
    }

    private fun hideBottomNav() {
        binding.bottomAppBar.animate().apply {
            duration = 1200
            translationY(1f)
        }

        binding.fab.animate().apply {
            duration = 1200
            translationY(1f)
        }
    }
}