package ru.spoonbill.droid.ui.catalog

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.spoonbill.app.R
import ru.spoonbill.app.databinding.FragmentCatalogBinding
import ru.spoonbill.app.utils.addRepeatedJob
import ru.spoonbill.app.utils.viewBinding
import ru.spoonbill.droid.ui.catalog.adapters.ParentCategoriesAdapter
import ru.spoonbill.droid.ui.catalog.model.ChildCategoryUi
import ru.spoonbill.droid.ui.fragments.BaseFragment
import ru.spoonbill.droid.ui.fragments.BottomNavState
import ru.spoonbill.droid.ui.fragments.mainBottomNavState
import ru.spoonbill.droid.ui.home.adapters.ProductCardAdapter
import ru.spoonbill.droid.ui.home.entity.ProductUi
import ru.spoonbill.droid.ui.single_product.SingleProductBottomSheetFragment
import ru.spoonbill.droid.ui.single_product.viewmodel.SingleProductSharedViewModel

class CatalogFragment : BaseFragment(R.layout.fragment_catalog) {

    private val binding by viewBinding(FragmentCatalogBinding::bind)
    private val viewModel by viewModel<CatalogViewModel>()
    private val sharedViewModel by sharedViewModel<SingleProductSharedViewModel>()

    private val recommendedProductsAdapter by lazy {
        ProductCardAdapter(
            ::showSingleProductBottomSheetFragment,
            viewModel::changeFavoriteStatus,
            ::showCartBottomSheetFragment
        )
    }
    private val parentCategoriesAdapter by lazy { ParentCategoriesAdapter(gridSpacing, ::navigateToSingleCategoryScreen) }

    private val gridSpacing by lazy { resources.getDimension(R.dimen.category_spacing).toInt() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews()
        collectUiStateFlow()
        viewModel.initialize()

        binding.nestedScrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if(oldScrollY >= scrollY) mainBottomNavState.value = BottomNavState.EXPANDED
            else mainBottomNavState.value = BottomNavState.COLLAPSED
        }
    }

    private fun collectUiStateFlow() {
        viewLifecycleOwner.addRepeatedJob(Lifecycle.State.STARTED) {
            viewModel.uiStateFlow.collectLatest { state ->
                parentCategoriesAdapter.submitList(state.parentCategories)
                recommendedProductsAdapter.submitList(state.recommendedProducts)
            }
        }
    }

    private fun setupViews() {
        setupParentRecyclerView()
        setupRecommendedProductsRecyclerView()
    }

    private fun setupParentRecyclerView() = binding.rvParentCategories.also {
        it.adapter = parentCategoriesAdapter
        it.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupRecommendedProductsRecyclerView() = binding.layoutProductParent.rvProducts.also {
        it.adapter = recommendedProductsAdapter
        it.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
    }

    private fun showSingleProductBottomSheetFragment(product: ProductUi) {
        sharedViewModel.selectedProduct = product
        SingleProductBottomSheetFragment.show(parentFragmentManager)
    }

    private fun navigateToSingleCategoryScreen(category: ChildCategoryUi) {
        val action = CatalogFragmentDirections.actionCatalogFragmentToSingleCategoryFragment(category.categoryId)
        findNavController().navigate(action)
    }

    override fun onStop() {
        super.onStop()

        mainBottomNavState.value = BottomNavState.EXPANDED
    }
}