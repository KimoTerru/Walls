package it.kimoterru.walls.ui.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import it.kimoterru.walls.R
import it.kimoterru.walls.adapter.WallpaperClickListener
import it.kimoterru.walls.adapter.topic.TopicAdapter
import it.kimoterru.walls.databinding.FragmentSavedBinding
import it.kimoterru.walls.models.photo.PhotoItem
import it.kimoterru.walls.util.Status.ERROR
import it.kimoterru.walls.util.Status.SUCCESS
import it.kimoterru.walls.util.gone
import it.kimoterru.walls.util.showToast
import it.kimoterru.walls.util.visible

@AndroidEntryPoint
class SavedFragment : Fragment(R.layout.fragment_saved), WallpaperClickListener.WallpaperClick {
    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SavedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllPhotosFromFavorite()
    }

    private fun initObservers() {
        viewModel.photoLiveData.observe(viewLifecycleOwner, {
            when (it.status) {
                SUCCESS -> {
                    displayImage(it.data)
                    showPhotoInFavorite()
                }
                ERROR -> {
                    emptyPhotoInFavorite()
                    showToast(it.message)
                }
                else -> emptyPhotoInFavorite()
            }
        })
    }

    private fun emptyPhotoInFavorite() {
        binding.emptyBox.visible()
        binding.emptyBoxText.visible()
        binding.saved.gone()
        binding.recyclerSavedWallpaper.gone()
    }

    private fun showPhotoInFavorite() {
        binding.emptyBox.gone()
        binding.emptyBoxText.gone()
        binding.saved.visible()
        binding.recyclerSavedWallpaper.visible()
    }

    private fun displayImage(response: List<PhotoItem>?) {
        binding.recyclerSavedWallpaper.adapter =
            TopicAdapter(response ?: listOf(), this, R.layout.card_image_display)
    }

    override fun onWallpaperClick(id: String, urlImageUser: String, idFavoritePhoto: Int) {
        Navigation.findNavController(requireView()).navigate(
            SavedFragmentDirections.actionFragmentSavedToFragmentSelectedImage(
                id, urlImageUser, 2, idFavoritePhoto
            )
        )
    }
}