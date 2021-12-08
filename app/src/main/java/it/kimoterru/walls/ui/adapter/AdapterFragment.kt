package it.kimoterru.walls.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import it.kimoterru.walls.R
import it.kimoterru.walls.adapter.WallpaperClickListener
import it.kimoterru.walls.adapter.search.SearchAdapter
import it.kimoterru.walls.adapter.topic.TopicAdapter
import it.kimoterru.walls.databinding.FragmentAdapterBinding
import it.kimoterru.walls.models.photo.PhotoItem
import it.kimoterru.walls.models.search.SearchItem
import it.kimoterru.walls.util.Status.ERROR
import it.kimoterru.walls.util.Status.SUCCESS
import it.kimoterru.walls.util.TopicsOrder

/*This snippet should contain: Fragments - image from search, color range and topics*/
@AndroidEntryPoint
class AdapterFragment : Fragment(R.layout.fragment_adapter),
    WallpaperClickListener.WallpaperClick {
    private var _binding: FragmentAdapterBinding? = null
    private val binding get() = _binding!!

    private val args: AdapterFragmentArgs by navArgs()
    private val viewModel: AdapterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAdapterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        setFragmentComponent()
    }

    override fun onResume() {
        super.onResume()
        // Determine which request
        when (args.whichSnippet) {
            1 -> viewModel.getImageTopics(args.query, TopicsOrder.LATEST)
            2 -> viewModel.getImageColors(args.query, args.query, TopicsOrder.LATEST)
            3 -> viewModel.getImageSearch(args.query, TopicsOrder.LATEST)
        }
    }

    private fun setFragmentComponent() {
        binding.nameSearch.text = args.tittle
        if (args.totalPhotos != 0) {
            binding.sizeSaveWallpaper.text = args.totalPhotos.toString()
            binding.sizeSaveWallpaper.visibility = View.VISIBLE
            binding.wAvailable.visibility = View.VISIBLE
        }
        val sGrid = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        sGrid.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        binding.recyclerImageSearch.layoutManager = sGrid
    } //For any garbage associated with onViewCreated

    private fun initObservers() {
        viewModel.imageTopicsLiveData.observe(viewLifecycleOwner, {
            when (it.status) {
                SUCCESS -> displayTopicImage(it.data)
                ERROR -> Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                else -> {
                }
            }
        })
        viewModel.imageLiveData.observe(viewLifecycleOwner, {
            when (it.status) {
                SUCCESS -> displaySearchImage(it.data!!)
                ERROR -> Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                else -> {
                }
            }
        }) // A request for color is immediately made
    }

    private fun displayTopicImage(response: List<PhotoItem>?) {
        binding.recyclerImageSearch.adapter =
            TopicAdapter(response ?: listOf(), this, R.layout.card_image_display)
    }

    private fun displaySearchImage(response: SearchItem) {
        binding.recyclerImageSearch.adapter =
            SearchAdapter(response, this, R.layout.card_image_display)
    } // A request for color is immediately made

    override fun onWallpaperClick(id: String, urlImageUser: String, idFavoritePhoto: Int) {
        Navigation.findNavController(requireView()).navigate(
            AdapterFragmentDirections.actionFragmentAdapterToFragmentSelectedImage(
                id, urlImageUser, 1, idFavoritePhoto
            )
        )
    }
}