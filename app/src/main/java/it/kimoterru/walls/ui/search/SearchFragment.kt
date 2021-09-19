package it.kimoterru.walls.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
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
    private var viewModel: SearchViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.search.text = args.query
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        initObservers()
        setFragmentComponent()
    }

    override fun onResume() {
        super.onResume()
        viewModel?.getImageSearch(args.query, TopicsOrder.LATEST)
    }

    private fun setFragmentComponent() {
        binding.search.text = args.query
    }

    private fun initObservers() {
        viewModel?.imageLiveData?.observe(viewLifecycleOwner, {
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
        binding.recyclerImageSearch.adapter =
            SearchAdapter(response, this, R.layout.card_image_search)
        binding.recyclerImageSearch.isNestedScrollingEnabled = false
    }

    override fun onWallpaperClick(
        id: String,
        urlImage: String,
        urlDownload: String,
        created: String,
        updated: String,
        userName: String,
        name: String,
    ) {
        Navigation.findNavController(requireView())
            .navigate(
                SearchFragmentDirections.actionFragmentSearchToFragmentSelectedImage(
                    id,
                    urlImage,
                    urlDownload,
                    created,
                    updated,
                    userName,
                    name
                ))
    }
}