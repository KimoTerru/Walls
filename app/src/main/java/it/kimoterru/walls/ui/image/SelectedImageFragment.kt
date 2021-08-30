package it.kimoterru.walls.ui.image

import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
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
        fragmentComponent()
    }

    private fun fragmentComponent() {
        binding.cardBrush.setOnClickListener(this)
        binding.cardDown.setOnClickListener(this)
        binding.cardInfo.setOnClickListener(this)
        hideFragmentComponent()
    }

    private fun hideFragmentComponent() {
        binding.cardBrush.visibility = View.GONE
        binding.cardDown.visibility = View.GONE
        binding.cardInfo.visibility = View.GONE
    }

    private fun showFragmentComponent() {
        binding.cardBrush.visibility = View.VISIBLE
        binding.cardDown.visibility = View.VISIBLE
        binding.cardInfo.visibility = View.VISIBLE
    }

    private fun initObservers() {
        binding.progressBar.showProgressBar()
        Glide.with(binding.selectedImage).load(args.urlImage).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                Toast.makeText(context, "Load failed", Toast.LENGTH_SHORT).show()
                binding.progressBar.showProgressBar()
                hideFragmentComponent()
                return false
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                binding.progressBar.hideProgressBar()
                showFragmentComponent()
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
                Toast.makeText(context, "Start download...", Toast.LENGTH_LONG).show()

                val link = args.urlDownload
                val dm: DownloadManager = requireActivity().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                val uri = Uri.parse(link)
                val fileName = args.idImage + ".jpg"
                val request = DownloadManager.Request(uri)
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
                request.setTitle(fileName)
                request.setDescription("Wait a second...")
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
                dm.enqueue(request)
            }
            it.kimoterru.walls.R.id.card_info -> {
                Toast.makeText(context, "card_info", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 15) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, "Great, now select an image", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Allow access to files", Toast.LENGTH_SHORT).show()
            }
        }
    }
}