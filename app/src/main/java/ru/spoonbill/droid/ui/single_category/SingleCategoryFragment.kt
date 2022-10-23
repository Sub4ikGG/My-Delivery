package ru.spoonbill.droid.ui.single_category

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.spoonbill.app.R
import ru.spoonbill.app.databinding.FragmentSingleCategoryBinding
import ru.spoonbill.app.utils.addRepeatedJob
import ru.spoonbill.app.utils.viewBinding
import ru.spoonbill.droid.ui.favorites.adapter.SimpleProductAdapter
import ru.spoonbill.droid.ui.fragments.BaseFragment
import ru.spoonbill.droid.ui.home.entity.ProductUi
import ru.spoonbill.droid.ui.single_product.SingleProductBottomSheetFragment
import ru.spoonbill.droid.ui.single_product.viewmodel.SingleProductSharedViewModel

class SingleCategoryFragment : BaseFragment(R.layout.fragment_single_category) {

    private val binding by viewBinding(FragmentSingleCategoryBinding::bind)
    private val viewModel by viewModel<SingleCategoryViewModel>()
    private val sharedViewModel by sharedViewModel<SingleProductSharedViewModel>()
    private val simpleProductsAdapter by lazy {
        SimpleProductAdapter(
            ::showSingleProductBottomSheetFragment,
            viewModel::changeFavoriteStatus,
            viewModel::changeQuantityInCart
        )
    }

    private val args by navArgs<SingleCategoryFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        collectUiStateFlow()
        viewModel.initialize(args.categoryId)
    }

    private fun setupViews() {
        binding.buttonToBack.setOnClickListener { findNavController().navigateUp() }
        setupProductsRecyclerView()
    }

    private fun setupProductsRecyclerView() {
        binding.rvSimpleProducts.adapter = simpleProductsAdapter
        binding.rvSimpleProducts.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun showSingleProductBottomSheetFragment(product: ProductUi) {
        sharedViewModel.selectedProduct = product
        SingleProductBottomSheetFragment.show(childFragmentManager)
    }

    private fun collectUiStateFlow() {
        viewLifecycleOwner.addRepeatedJob(Lifecycle.State.STARTED) {
            viewModel.uiStateFlow.collectLatest { state ->
                state.category?.let { binding.textCategoryName.text = it.name }
                simpleProductsAdapter.submitList(state.products)
            }
        }
    }
}