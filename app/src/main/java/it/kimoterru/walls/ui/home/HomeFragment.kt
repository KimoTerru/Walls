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
import it.kimoterru.walls.data.remote.models.photo.PhotoResponse
import it.kimoterru.walls.data.remote.models.topic.TopicResponse
import it.kimoterru.walls.data.repository.getColors
import it.kimoterru.walls.databinding.FragmentHomeBinding
import it.kimoterru.walls.util.Constants.Companion.colors
import it.kimoterru.walls.util.Constants.Companion.notSaved
import it.kimoterru.walls.util.Constants.Companion.search
import it.kimoterru.walls.util.Constants.Companion.topics
import it.kimoterru.walls.util.Constants.Companion.zero
import it.kimoterru.walls.util.Status.*
import it.kimoterru.walls.util.TopicsOrder
import it.kimoterru.walls.util.WallpaperClickListener
import it.kimoterru.walls.util.isVisible
import it.kimoterru.walls.util.visible

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
                    binding.homeBoxView.root.visible()
                    errorStateConnection(it.message.toString(), false)
                    showLoadingAnim(false)
                }
                ERROR -> {
                    errorStateConnection(it.message.toString(), true)
                    showLoadingAnim(false)
                }
                LOADING -> showLoadingAnim(true)
            }
        }
        viewModel.topicsLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                SUCCESS -> {
                    displayTopics(it.data)
                    displayColors()
                    binding.homeBoxView.root.visible()
                    showLoadingAnim(false)
                }
                /*ERROR -> showToast(it.message)*/
                else -> {
                }
            }
        }
    }

    private fun showLoadingAnim(show: Boolean) {
        //binding.homeAnimationView.isVisible(show)
    }

    private fun errorStateConnection(error: String, show: Boolean) {
        binding.errorBoxView.root.isVisible(show)
        binding.errorBoxView.errorMassageView.text = error
    }

    private fun displayLatest(response: List<PhotoResponse>?) {
        binding.homeBoxView.recyclerLatestWallpapers.adapter =
            response?.let { LatestAdapter(it, this) }
    }

    private fun displayColors() {
        binding.homeBoxView.recyclerBestColorTone.adapter = ColorAdapter(getColors(), this)
    }

    private fun displayTopics(list: List<TopicResponse>?) {
        binding.homeBoxView.recyclerCategories.adapter = list?.let { TopicAdapter(it, this) }
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
                            query, query, 0, search
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
                id, urlImageUser, notSaved, idFavoritePhoto
            )
        )
    }

    override fun onColorClick(name: String) {
        Navigation.findNavController(requireView()).navigate(
            HomeFragmentDirections.actionFragmentHomeToFragmentSearch(
                name, name, zero, colors
            )
        )
    }

    override fun onTopicClick(name: String, tittle: String, totalPhotos: Int) {
        Navigation.findNavController(requireView())
            .navigate(
                HomeFragmentDirections.actionFragmentHomeToFragmentSearch(
                    name, tittle, totalPhotos, topics
                )
            )
    }
}