package it.kimoterru.walls.ui.image

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import it.kimoterru.walls.R
import it.kimoterru.walls.databinding.BottomSheetApplyToScreenBinding
import it.kimoterru.walls.databinding.BottomSheetInfoBinding
import it.kimoterru.walls.databinding.FragmentSelectedImageBinding
import it.kimoterru.walls.models.photo.PhotoItem

@AndroidEntryPoint
class SelectedImageFragment : Fragment(R.layout.fragment_selected_image) {
    private var _binding: FragmentSelectedImageBinding? = null
    private val binding get() = _binding!!

    private val args: SelectedImageFragmentArgs by navArgs()
    private val viewModel: SelectedImageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectedImageBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        hideFragmentComponent()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPhoto(args.idImage)
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
        viewModel.photoLiveData.observe(viewLifecycleOwner, {
            binding.progressBar.showProgressBar()
            Glide.with(binding.selectedImage).load(it.urls.regular)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?, model: Any?, target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Toast.makeText(context, "Load failed", Toast.LENGTH_SHORT).show()
                        binding.progressBar.showProgressBar()
                        hideFragmentComponent()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?, model: Any?, target: Target<Drawable>?,
                        dataSource: DataSource?, isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBar.hideProgressBar()
                        showFragmentComponent()
                        return false
                    }
                }).into(binding.selectedImage)
            onClick(it)
        })
    }

    @SuppressLint("SetTextI18n", "NewApi")
    private fun onClick(data: PhotoItem) {
        binding.cardBrush.setOnClickListener {
            val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogApplyToTheme)
            val bindingBottomSheet = BottomSheetApplyToScreenBinding.inflate(layoutInflater)
            dialog.setContentView(bindingBottomSheet.root)

            bindingBottomSheet.let {
                it.applyToDesktop.setOnClickListener {
                    val wallpaperManager = WallpaperManager.getInstance(context)
                    val bitmap = (binding.selectedImage.drawable as BitmapDrawable).bitmap
                    wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_SYSTEM)
                    dialog.hide()
                }
                it.applyToLockScreen.setOnClickListener {
                    val wallpaperManager = WallpaperManager.getInstance(context)
                    val bitmap = (binding.selectedImage.drawable as BitmapDrawable).bitmap
                    wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_LOCK)
                    dialog.hide()
                }
            }
            dialog.show()
        }
        binding.cardDown.setOnClickListener {
            Toast.makeText(context, "Start download...", Toast.LENGTH_LONG).show()

            val dm: DownloadManager =
                requireActivity().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val fileName = data.id + ".jpg"
            val request = DownloadManager.Request(Uri.parse(data.links.download))
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
            request.setTitle(fileName)
            request.setDescription("Wait a second...")
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_PICTURES, "/Walls/$fileName"
            )
            dm.enqueue(request)
        }
        binding.cardInfo.setOnClickListener {
            val dialog = BottomSheetDialog(requireContext(), R.style.AppBottomSheetDialogTheme)
            val bindingBottomSheet = BottomSheetInfoBinding.inflate(layoutInflater)
            dialog.setContentView(bindingBottomSheet.root)

            bindingBottomSheet.let {
                //all views in bindingBottomSheet
                Glide.with(it.imageInfoUser).load(args.urlImageUser).into(it.imageInfoUser)
                it.infoUser.text = data.user.name
                it.infoLocation.text = "${data.location.city}/${data.location.country}"
                it.idImageInfo.text = data.id
                it.resolutionInfo.text = "${data.width}x${data.height}"
                it.createdAtInfo.text = data.createdAt
                it.colorInfo.text = data.color
                it.downInfo.text = data.downloads.toString()
            }
            dialog.show()
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