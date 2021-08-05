package it.kimoterru.walls.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import it.kimoterru.walls.R
import it.kimoterru.walls.adapter.CategoriesAdapter
import it.kimoterru.walls.adapter.MyAdapter
import it.kimoterru.walls.adapter.WallpaperClickListener
import it.kimoterru.walls.databinding.FragmentHomeBinding
import it.kimoterru.walls.models.PhotoItem
import it.kimoterru.walls.ui.home.categories.Categories
import it.kimoterru.walls.ui.image.SelectedImageActivity
import it.kimoterru.walls.util.Status
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), WallpaperClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var viewModel: HomeViewModel? = null

    private var categories: ArrayList<Categories> = ArrayList<Categories>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        initObservers()
        findWallpaper()
        setInitialDataCategories()
    }

    override fun onResume() {
        super.onResume()
        viewModel?.getHomeScreen()
    }

    private fun initObservers() {
        viewModel?.homeResponseLiveData?.observe(viewLifecycleOwner, {
            binding.progressBar.isVisible = it.status == Status.LOADING
            binding.latestWallpapers.isVisible = it.status == Status.SUCCESS
            binding.theColorTone.isVisible = it.status == Status.SUCCESS
            binding.categories.isVisible = it.status == Status.SUCCESS
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

    private fun displayData(response: List<PhotoItem>?) {
        binding.recyclerLatestWallpapers.adapter = MyAdapter(response ?: listOf(), this, R.layout.card_image)
        binding.recyclerBestColorTone.adapter = MyAdapter(response ?: listOf(), this, R.layout.card_color) // TODO: 24.07.2021
        binding.recyclerCategories.adapter = CategoriesAdapter(categories)
    }

    private fun findWallpaper() {
        binding.findImage.addTextChangedListener {
            binding.searchImage.isVisible = it.toString().isNotEmpty()
            binding.searchImage.setOnClickListener {
                binding.findImage.setText("Let's try again!")
            }
            /*if (it.toString().isNotEmpty()) {
                binding.searchImage.setImageResource(R.drawable.close)
            }*/
        }
    } // TODO: 28.07.2021

    private fun setInitialDataCategories() {
        categories.add(Categories(R.drawable.abstrack, "Abstrack"))
        categories.add(Categories(R.drawable.anime, "Anime"))
        categories.add(Categories(R.drawable.architecture, "Architecture"))
        categories.add(Categories(R.drawable.beach, "Beach"))
        categories.add(Categories(R.drawable.cars, "Cars"))
        categories.add(Categories(R.drawable.cats, "Cats"))
        categories.add(Categories(R.drawable.dogs, "Dogs"))
        categories.add(Categories(R.drawable.landscape, "Landscape"))
        categories.add(Categories(R.drawable.movies, "Movies"))
        categories.add(Categories(R.drawable.nature, "Nature"))
        categories.add(Categories(R.drawable.space, "Space"))
        categories.add(Categories(R.drawable.sunset, "Sunset"))
        categories.add(Categories(R.drawable.video_game, "Video games"))
        categories.add(Categories(R.drawable.women, "Women"))
    }

    override fun onWallpaperClick(id: String) {
        val selectedImage = Intent(context, SelectedImageActivity::class.java)
        selectedImage.putExtra("key_image", id)
        startActivity(selectedImage)
    }
}