package it.kimoterru.walls.ui.image

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dagger.hilt.android.AndroidEntryPoint
import it.kimoterru.walls.databinding.ActivitySelectedImageBinding

@AndroidEntryPoint
class SelectedImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectedImageBinding
    private lateinit var viewModel: SelectedImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectedImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(SelectedImageViewModel::class.java)

        checkIntent()
        initObservers()
    }

    private fun checkIntent() {
        val imageId = intent.getStringExtra("key_image")
        if (imageId?.isNotEmpty() == true) {
            showImageData(imageId)
        } else finish()
    }

    private fun showImageData(imageId: String) {
        viewModel.getPhoto(imageId)
    }

    private fun initObservers() {
        binding.progressBar.showProgressBar()
        viewModel.photoLiveData.observe(this) {
            Glide.with(binding.selectedImage).load(it.urls.full).listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    Toast.makeText(this@SelectedImageActivity, "Load failed", Toast.LENGTH_SHORT).show()
                    binding.progressBar.showProgressBar()
                    finish()
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