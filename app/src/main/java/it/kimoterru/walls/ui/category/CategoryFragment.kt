package it.kimoterru.walls.ui.category

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
import it.kimoterru.walls.adapter.category.CategoryAdapter
import it.kimoterru.walls.databinding.FragmentCategoriesBinding
import it.kimoterru.walls.models.photo.PhotoItem
import it.kimoterru.walls.util.Status
import it.kimoterru.walls.util.TopicsOrder

@AndroidEntryPoint
class CategoryFragment : Fragment(R.layout.fragment_categories),
    WallpaperClickListener.WallpaperClick {
    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private val args: CategoryFragmentArgs by navArgs()
    private var viewModel: CategoryViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)

        initObservers()
        setFragmentComponent()
    }

    override fun onResume() {
        super.onResume()
        viewModel?.getImageTopics(args.slug, TopicsOrder.LATEST)
    }

    private fun setFragmentComponent() {
        binding.category.text = args.tittle
        binding.sizeSaveWallpaper.text = args.totalPhotos.toString()
    }

    private fun initObservers() {
        viewModel?.imageTopicsLiveData?.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    displayImage(it.data)
                }
                Status.ERROR -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
                else -> {
                }
            }
        })
    }

    private fun displayImage(response: List<PhotoItem>?) {
        binding.recyclerImageCategory.adapter =
            CategoryAdapter(response ?: listOf(), this, R.layout.card_image_category)
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
                CategoryFragmentDirections.actionFragmentCategoriesToFragmentSelectedImage(
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