package it.kimoterru.walls.ui.image

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dagger.hilt.android.AndroidEntryPoint
import it.kimoterru.walls.R
import it.kimoterru.walls.databinding.FragmentSelectedImageBinding

@AndroidEntryPoint
class SelectedImageFragment : Fragment(R.layout.fragment_selected_image) {
    private var _binding: FragmentSelectedImageBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SelectedImageViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSelectedImageBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SelectedImageViewModel::class.java)

        initObservers()
        //checkIntent()
    }

    /*private fun checkIntent() {
        val imageId = intent.getStringExtra("key_image")
        if (imageId?.isNotEmpty() == true) {
            showImageData(imageId)
        } else finish()
    }*/

    private fun showImageData(imageId: String) {
        viewModel.getPhoto(imageId)
    }

    private fun initObservers() {
        binding.progressBar.showProgressBar()
        viewModel.photoLiveData.observe(viewLifecycleOwner) {
            Glide.with(binding.selectedImage).load(it.urls.full).listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    Toast.makeText(context, "Load failed", Toast.LENGTH_SHORT).show()
                    binding.progressBar.showProgressBar()
                    onDestroy()
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    //todo hide progressbar
                    binding.progressBar.hideProgressBar()
                    return false
                }
            }).into(binding.selectedImage)
        }
    }
}