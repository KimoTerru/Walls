package it.kimoterru.walls.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import it.kimoterru.walls.R
import it.kimoterru.walls.databinding.FragmentSearchBinding
import it.kimoterru.walls.util.*
import it.kimoterru.walls.util.Status.*

/*This snippet should contain: Fragments - image from search, color range and topics*/
@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search), WallpaperClickListener.WallpaperClick {

    private val binding: FragmentSearchBinding by viewBinding()
    private val args: SearchFragmentArgs by navArgs()
    private val viewModel: SearchViewModel by viewModels()

    private val searchAdapter by lazy {
        SearchAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        fragmentComponent()
        initRecycler()
    }

    private fun initRecycler() {
        val sGrid = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        sGrid.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        binding.recyclerImageSearch.apply {
            layoutManager = sGrid
            adapter = searchAdapter
            addOnScrollListener(object : PaginationScrollListener(sGrid) {
                override fun loadMoreItems() {
                    viewModel.isLoading = true
                    viewModel.pagePhoto++
                    viewModel.addData()
                }

                override val isLastPage: Boolean
                    get() = viewModel.isLastPage
                override val isLoading: Boolean
                    get() = viewModel.isLoading
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun fragmentComponent() {
        binding.nameSearch.text = args.tittle
        if (args.totalPhotos != 0) {
            binding.sizeSaveWallpaper.text =
                args.totalPhotos.toString() + " " + getText(R.string.wallpapers_available)
            binding.sizeSaveWallpaper.visible()
        } else {
            binding.sizeSaveWallpaper.gone()
        }
        setupSwipeRefreshLayout()
    } //For any garbage associated with onViewCreated

    private fun setupSwipeRefreshLayout() {
        binding.searchSwipeRefreshLayout.apply {
            setColorSchemeResources(R.color.wp_blue)
            setOnRefreshListener {
                viewModel.updateDate()
            }
            setProgressBackgroundColorSchemeResource(R.color.my_day_night)
        }
    }

    private fun initObservers() {
        viewModel.imageTopicsLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                SUCCESS -> {
                    binding.errorLayoutSearch.root.gone()
                    binding.searchSwipeRefreshLayout.isRefreshing = false
                    viewModel.isLoading = false
                    it.data?.let { list -> searchAdapter.addData(list) }
                }
                ERROR -> {
                    binding.searchSwipeRefreshLayout.isRefreshing = false
                    binding.errorLayoutSearch.apply {
                        root.visible()
                        errorMassageView.text = it.message
                        repeatButtonView.setOnClickListener {
                            viewModel.updateDate()
                        }
                    }
                    binding.searchSwipeRefreshLayout.isRefreshing = false
                }
                LOADING -> {
                    binding.errorLayoutSearch.root.gone()
                    binding.searchSwipeRefreshLayout.isRefreshing = !viewModel.isLoading
                }
            }
        }
        viewModel.imageSearchLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                SUCCESS -> {
                    binding.errorLayoutSearch.root.gone()
                    binding.searchSwipeRefreshLayout.isRefreshing = false
                    viewModel.isLoading = false
                    it.data?.let { list -> searchAdapter.addData(list.resultsPhoto) }
                }
                ERROR -> {
                    binding.searchSwipeRefreshLayout.isRefreshing = false
                    binding.errorLayoutSearch.apply {
                        root.visible()
                        errorMassageView.text = it.message
                        repeatButtonView.setOnClickListener {
                            viewModel.updateDate()
                        }
                    }
                }
                LOADING -> {
                    binding.errorLayoutSearch.root.gone()
                    binding.searchSwipeRefreshLayout.isRefreshing = !viewModel.isLoading
                }
            }
        } // A request for color is immediately made
    }

    override fun onWallpaperClick(idNetworkPhoto: String, idLocalPhoto: Int, urlImageUser: String) {
        Navigation.findNavController(requireView()).navigate(
            SearchFragmentDirections.actionFragmentSearchToActivityDetailImage(
                idNetworkPhoto, idLocalPhoto, urlImageUser
            )
        )
    }
}