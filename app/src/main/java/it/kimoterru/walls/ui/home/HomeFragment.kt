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
import it.kimoterru.walls.data.repository.getColors
import it.kimoterru.walls.databinding.FragmentHomeBinding
import it.kimoterru.walls.domain.models.photo.Photo
import it.kimoterru.walls.domain.models.topic.Topic
import it.kimoterru.walls.util.*
import it.kimoterru.walls.util.Constants.Companion.colors
import it.kimoterru.walls.util.Constants.Companion.latest
import it.kimoterru.walls.util.Constants.Companion.latestWallpapers
import it.kimoterru.walls.util.Constants.Companion.search
import it.kimoterru.walls.util.Constants.Companion.topics
import it.kimoterru.walls.util.Constants.Companion.zero
import it.kimoterru.walls.util.Status.*

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), WallpaperClickListener.HomeFragment {

    private val binding: FragmentHomeBinding by viewBinding()
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initSearch()
    }

    private fun initObservers() {
        viewModel.homeResponseLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                SUCCESS -> {
                    binding.homeAnimView.gone()
                    displayLatest(it.data)
                    errorStateConnection(it.message.toString(), false)
                }
                ERROR -> {
                    binding.homeAnimView.gone()
                    errorStateConnection(it.message.toString(), true)
                }
                LOADING -> {
                    binding.errorLayoutView.root.gone()
                    binding.homeAnimView.visible()
                }
            }
        }
        viewModel.topicsLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                SUCCESS -> {
                    binding.homeAnimView.gone()
                    displayTopics(it.data)
                    displayColors()
                    binding.homeContentLayoutView.root.visible()
                }
                ERROR -> {
                    binding.homeAnimView.gone()
                    errorStateConnection(it.message.toString(), true)
                }
                LOADING -> {
                    binding.errorLayoutView.root.gone()
                    binding.homeAnimView.visible()
                }
            }
        }
    }

    private fun errorStateConnection(error: String, show: Boolean) {
        binding.errorLayoutView.apply {
            root.isVisible(show)
            errorMassageView.text = error
            repeatButtonView.setOnClickListener {
                viewModel.updateData()
            }
        }
    }

    private fun displayLatest(response: List<Photo>?) {
        binding.homeContentLayoutView.recyclerLatestWallpapers.adapter =
            response?.let { LatestAdapter(it, this) }
    }

    private fun displayColors() {
        binding.homeContentLayoutView.recyclerBestColorTone.adapter = ColorAdapter(getColors(), this)
    }

    private fun displayTopics(list: List<Topic>?) {
        binding.homeContentLayoutView.recyclerCategories.adapter = list?.let { TopicAdapter(it, this) }
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

    override fun onLatestPhoto() {
        Navigation.findNavController(requireView()).navigate(
            HomeFragmentDirections.actionFragmentHomeToFragmentSearch(
                latest, latestWallpapers, zero, latest
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