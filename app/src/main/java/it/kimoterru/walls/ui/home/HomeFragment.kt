package it.kimoterru.walls.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import it.kimoterru.walls.R
import it.kimoterru.walls.adapter.WallpaperClickListener
import it.kimoterru.walls.adapter.home.CategoriesAdapter
import it.kimoterru.walls.adapter.home.ColorAdapter
import it.kimoterru.walls.adapter.home.LatestAdapter
import it.kimoterru.walls.databinding.FragmentHomeBinding
import it.kimoterru.walls.models.categories.TopicItem
import it.kimoterru.walls.models.photo.PhotoItem
import it.kimoterru.walls.util.Status
import it.kimoterru.walls.util.TopicsOrder

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), WallpaperClickListener.WallpaperClick, WallpaperClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var viewModel: HomeViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        initObservers()
        initSearch()
    }

    override fun onResume() {
        super.onResume()
        viewModel?.getHomeScreen()
        viewModel?.getTopics(TopicsOrder.POSITION)
    }

    private fun initObservers() {
        viewModel?.homeResponseLiveData?.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    displayLatest(it.data)
                    hideShimmerEffectLatestWallpapers()
                }
                Status.ERROR -> {
                    noNetworkConnect()
                }
                else -> {}
            }
        })
        viewModel?.topicsLiveData?.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    displayTopics(it.data)
                    hideShimmerEffectCategories()
                    displayColors()
                }
                Status.ERROR -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        })
    }

    private fun hideShimmerEffectLatestWallpapers() {
        binding.shimmerLayout.stopShimmer()
        binding.shimmerLayout.visibility = View.GONE
    }

    private fun hideShimmerEffectCategories() {
        binding.shimmerLayoutCategories.stopShimmer()
        binding.shimmerLayoutCategories.visibility = View.GONE

        binding.shimmerLayoutColor.stopShimmer()
        binding.shimmerLayoutColor.visibility = View.GONE
    }

    private fun noNetworkConnect() {
        Navigation.findNavController(requireView()).navigate(R.id.action_fragment_home_to_fragment_no_internet)
    }

    private fun displayLatest(response: List<PhotoItem>?) {
        binding.recyclerLatestWallpapers.adapter = LatestAdapter(response ?: listOf(), this, R.layout.card_image)
    }

    private fun displayColors() {
        binding.recyclerBestColorTone.adapter = ColorAdapter(this, R.layout.card_color)
    }

    private fun displayTopics(list: List<TopicItem>?) {
        binding.recyclerCategories.adapter = CategoriesAdapter(list!!, this)
        binding.recyclerCategories.isNestedScrollingEnabled = false
    }

    private fun initSearch() {
        binding.findImage.addTextChangedListener {
            binding.searchImage.isVisible = it.toString().isEmpty()
            binding.searchImage.setOnClickListener {
                binding.findImage.setText("Let's try again!")
            }
            /*if (it.toString().isNotEmpty()) {
                binding.searchImage.setImageResource(R.drawable.close)
            }*/
        } // FIXME: 04.09.2021
        binding.findImage.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query: String = binding.findImage.text.toString()
                if (query.isNotEmpty()) {
                    Navigation.findNavController(requireView())
                        .navigate(HomeFragmentDirections.actionFragmentHomeToFragmentSearch(query))
                } else {
                    Toast.makeText(context, "Error!", Toast.LENGTH_LONG).show()
                }
                true
            } else false
        }
    }

    override fun onWallpaperClick(
        id: String,
        urlImage: String,
        urlDownload: String,
        created: String,
        updated: String,
        userName: String,
        name: String
    ) {
        Navigation.findNavController(requireView())
            .navigate(
                HomeFragmentDirections.actionFragmentHomeToFragmentSelectedImage(
                    id,
                    urlImage,
                    urlDownload,
                    created,
                    updated,
                    userName,
                    name
                ))
    }

    override fun onColorClick(name: String) {
        Navigation.findNavController(requireView())
            .navigate(HomeFragmentDirections.actionFragmentHomeToFragmentColors(
                name
            ))
    }

    override fun onCategoryClick(name: String, tittle: String, totalPhotos: Int) {
        Navigation.findNavController(requireView())
            .navigate(HomeFragmentDirections.actionFragmentHomeToFragmentCategories(
                name,
                tittle,
                totalPhotos
            ))
    }
}