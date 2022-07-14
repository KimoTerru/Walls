package it.kimoterru.walls.ui.home

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import it.kimoterru.walls.R
import it.kimoterru.walls.data.remote.models.categories.TopicItem
import it.kimoterru.walls.data.remote.models.photo.PhotoItem
import it.kimoterru.walls.data.repository.getColors
import it.kimoterru.walls.databinding.FragmentHomeBinding
import it.kimoterru.walls.util.Status.ERROR
import it.kimoterru.walls.util.Status.SUCCESS
import it.kimoterru.walls.util.TopicsOrder
import it.kimoterru.walls.util.WallpaperClickListener
import it.kimoterru.walls.util.gone
import it.kimoterru.walls.util.showToast

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), WallpaperClickListener.WallpaperClick,
    WallpaperClickListener.HomeFragment {

    private val binding: FragmentHomeBinding by viewBinding()
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initSearch()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getHomeScreen()
        viewModel.getTopics(TopicsOrder.POSITION)
    }

    private fun initObservers() {
        viewModel.homeResponseLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                SUCCESS -> {
                    displayLatest(it.data)
                    hideShimmerEffectLatestWallpapers()
                }
                ERROR -> {
                    showToast(it.message)
                    noNetworkConnect()
                }
                else -> {
                }
            }
        }
        viewModel.topicsLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                SUCCESS -> {
                    displayTopics(it.data)
                    hideShimmerEffectCategories()
                    displayColors()
                }
                ERROR -> showToast(it.message)
                else -> {
                }
            }
        }
    }

    private fun hideShimmerEffectLatestWallpapers() {
        binding.shimmerLayout.stopShimmer()
        binding.shimmerLayout.gone()
    }

    private fun hideShimmerEffectCategories() {
        binding.shimmerLayoutCategories.stopShimmer()
        binding.shimmerLayoutCategories.gone()

        binding.shimmerLayoutColor.stopShimmer()
        binding.shimmerLayoutColor.gone()
    }

    private fun noNetworkConnect() {
        Navigation.findNavController(requireView())
            .navigate(R.id.action_fragment_home_to_fragment_no_internet)
    }

    private fun displayLatest(response: List<PhotoItem>?) {
        binding.recyclerLatestWallpapers.adapter =
            response?.let { LatestAdapter(it, this, R.layout.card_image) }
    }

    private fun displayColors() {
        binding.recyclerBestColorTone.adapter =
            ColorAdapter(getColors(), this, R.layout.card_color)
    }

    private fun displayTopics(list: List<TopicItem>?) {
        binding.recyclerCategories.adapter = list?.let { CategoryAdapter(it, this) }
        binding.recyclerCategories.isNestedScrollingEnabled = false
    }

    private fun initSearch() {
        binding.findImage.addTextChangedListener {
            if (it.toString().isNotEmpty()) {
                binding.searchImage.setImageResource(R.drawable.cancel)
                binding.searchImage.setOnClickListener {
                    binding.findImage.text.clear()
                }
            } else {
                binding.searchImage.setImageResource(R.drawable.search)
            }
        }
        binding.findImage.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query: String = binding.findImage.text.toString()
                if (query.isNotEmpty()) {
                    Navigation.findNavController(requireView()).navigate(
                        HomeFragmentDirections.actionFragmentHomeToFragmentSearch(
                            query, query, 0, 3
                        )
                    )
                } else {
                    Toast.makeText(context, "Empty!", Toast.LENGTH_LONG).show()
                }
                true
            } else false
        }
    }

    override fun onWallpaperClick(id: String, urlImageUser: String, idFavoritePhoto: Int) {
        Navigation.findNavController(requireView()).navigate(
            HomeFragmentDirections.actionFragmentHomeToFragmentSelectedImage(
                id, urlImageUser, 1, idFavoritePhoto
            )
        )
    }

    override fun onColorClick(name: String) {
        Navigation.findNavController(requireView()).navigate(
            HomeFragmentDirections.actionFragmentHomeToFragmentSearch(
                name, name, 0, 2
            )
        )
    }

    override fun onTopicClick(name: String, tittle: String, totalPhotos: Int) {
        Navigation.findNavController(requireView())
            .navigate(
                HomeFragmentDirections.actionFragmentHomeToFragmentSearch(
                    name, tittle, totalPhotos, 1
                )
            )
    }
}