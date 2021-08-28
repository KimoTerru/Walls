package it.kimoterru.walls.ui.image

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dagger.hilt.android.AndroidEntryPoint
import it.kimoterru.walls.databinding.FragmentSelectedImageBinding

@AndroidEntryPoint
class SelectedImageFragment : Fragment(it.kimoterru.walls.R.layout.fragment_selected_image), View.OnClickListener {
    private var _binding: FragmentSelectedImageBinding? = null
    private val binding get() = _binding!!

    private val args: SelectedImageFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSelectedImageBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        otherFunctions()
    }

    private fun otherFunctions() {
        binding.cardBrush.setOnClickListener(this)
        binding.cardDown.setOnClickListener(this)
        binding.cardInfo.setOnClickListener(this)
    }

    private fun initObservers() {
        binding.progressBar.showProgressBar()
        Glide.with(binding.selectedImage).load(args.urlImage).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                Toast.makeText(context, "Load failed", Toast.LENGTH_SHORT).show()
                binding.progressBar.showProgressBar()
                return false
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                binding.progressBar.hideProgressBar()
                return false
            }
        }).into(binding.selectedImage)
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            it.kimoterru.walls.R.id.card_brush -> {
                Toast.makeText(context, "card_brush", Toast.LENGTH_LONG).show()
            }
            it.kimoterru.walls.R.id.card_down -> {
                Toast.makeText(context, "card_down", Toast.LENGTH_LONG).show()
            }
            it.kimoterru.walls.R.id.card_info -> {
                Toast.makeText(context, "card_info", Toast.LENGTH_LONG).show()
            }
        }
    }
}