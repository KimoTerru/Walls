package it.kimoterru.walls.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import it.kimoterru.walls.R
import it.kimoterru.walls.adapter.MyAdapter
import it.kimoterru.walls.adapter.WallpaperClickListener
import it.kimoterru.walls.databinding.FragmentHomeBinding
import it.kimoterru.walls.model.Wallpaper
import it.kimoterru.walls.util.Status

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
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
    }

    override fun onResume() {
        super.onResume()
        viewModel?.getHomeScreen()
    }

    private fun initObservers() {
        viewModel?.homeResponseLiveData?.observe(viewLifecycleOwner, {
            binding.progressBar.isVisible = it.status == Status.LOADING
            when (it.status) {
                Status.SUCCESS -> {
                    displayData(it.data)
                }
                Status.ERROR -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        })
    }

    private fun displayData(response: Wallpaper?) {
        binding.recyclerBestOfTheMonth.adapter = MyAdapter(response?.data ?: listOf(), R.layout.card_image)
        binding.recyclerBestColorTone.adapter = MyAdapter(response?.data ?: listOf(), R.layout.card_color) // TODO: 24.07.2021
        binding.recyclerCategories.adapter = MyAdapter(response?.data ?: listOf(), R.layout.card_categories)
    }
}