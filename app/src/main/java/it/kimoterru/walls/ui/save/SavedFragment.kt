package it.kimoterru.walls.ui.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import it.kimoterru.walls.R
import it.kimoterru.walls.adapter.WallpaperClickListener
import it.kimoterru.walls.adapter.saved.SavedAdapter
import it.kimoterru.walls.databinding.FragmentSavedBinding
import it.kimoterru.walls.util.Status.ERROR
import it.kimoterru.walls.util.Status.SUCCESS
import it.kimoterru.walls.util.gone
import it.kimoterru.walls.util.visible

@AndroidEntryPoint
class SavedFragment : Fragment(R.layout.fragment_saved), WallpaperClickListener.WallpaperClick {
    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SavedViewModel by viewModels()
    private val savedAdapter by lazy {
        SavedAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllPhotosFromFavorite()
        initObservers()
        val sGrid = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        sGrid.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        binding.recyclerSavedWallpaper.layoutManager = sGrid
        binding.recyclerSavedWallpaper.adapter = savedAdapter
    }

    private fun initObservers() {
        viewModel.photoLiveData.observe(viewLifecycleOwner, {
            when (it.status) {
                SUCCESS -> {
                    it.data?.let { it1 -> savedAdapter.updateItems(it1) }
                    showPhotoInFavorite()
                }
                ERROR -> {
                    emptyPhotoInFavorite()
                }
                else -> emptyPhotoInFavorite()
            }
        })
    }

    private fun emptyPhotoInFavorite() {
        binding.emptyBox.root.visible()
        binding.saved.gone()
        binding.recyclerSavedWallpaper.gone()
    }

    private fun showPhotoInFavorite() {
        binding.emptyBox.root.gone()
        binding.saved.visible()
        binding.recyclerSavedWallpaper.visible()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onWallpaperClick(id: String, urlImageUser: String, idFavoritePhoto: Int) {
        Navigation.findNavController(requireView()).navigate(
            SavedFragmentDirections.actionFragmentSavedToFragmentSelectedImage(
                id, urlImageUser, 2, idFavoritePhoto
            )
        )
    }
}