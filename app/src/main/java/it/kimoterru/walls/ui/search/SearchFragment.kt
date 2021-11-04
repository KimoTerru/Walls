package it.kimoterru.walls.ui.search

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
import it.kimoterru.walls.adapter.searsh.SearchAdapter
import it.kimoterru.walls.databinding.FragmentSearchBinding
import it.kimoterru.walls.models.search.SearchItem
import it.kimoterru.walls.util.Status
import it.kimoterru.walls.util.TopicsOrder

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search),
    WallpaperClickListener.WallpaperClick {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val args: SearchFragmentArgs by navArgs()
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.search.text = args.query

        initObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getImageSearch(args.query, TopicsOrder.LATEST)
    }

    private fun initObservers() {
        viewModel.imageLiveData.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    displayImage(it.data!!)
                }
                Status.ERROR -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
                else -> {
                }
            }
        })
    }

    private fun displayImage(response: SearchItem) {
        val sGrid = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        sGrid.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        binding.recyclerImageSearch.layoutManager = sGrid

        binding.recyclerImageSearch.adapter =
            SearchAdapter(response, this, R.layout.card_image_display)
    }

    override fun onWallpaperClick(id: String, urlImageUser: String) {
        Navigation.findNavController(requireView()).navigate(
            SearchFragmentDirections.actionFragmentSearchToFragmentSelectedImage(
                id, urlImageUser
            )
        )
    }
}