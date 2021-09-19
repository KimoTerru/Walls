package it.kimoterru.walls.ui.color

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
import it.kimoterru.walls.adapter.color.ColorAdapter
import it.kimoterru.walls.databinding.FragmentColorsBinding
import it.kimoterru.walls.models.search.SearchItem
import it.kimoterru.walls.util.Status
import it.kimoterru.walls.util.TopicsOrder

@AndroidEntryPoint
class ColorsFragment : Fragment(R.layout.fragment_categories),
    WallpaperClickListener.WallpaperClick {
    private var _binding: FragmentColorsBinding? = null
    private val binding get() = _binding!!

    private val args: ColorsFragmentArgs by navArgs()
    private var viewModel: ColorsViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentColorsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ColorsViewModel::class.java)

        initObservers()
        setFragmentComponent()
    }

    override fun onResume() {
        super.onResume()
        viewModel?.getImageColors(args.tittle, args.tittle, TopicsOrder.LATEST)
    }

    private fun setFragmentComponent() {
        binding.color.text = args.tittle
    }

    private fun initObservers() {
        viewModel?.imageColorLiveData?.observe(viewLifecycleOwner, {
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
        binding.recyclerImageColors.adapter =
            ColorAdapter(response, this, R.layout.card_image_display)
        binding.recyclerImageColors.isNestedScrollingEnabled = false
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
                ColorsFragmentDirections.actionFragmentColorsToFragmentSelectedImage(
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