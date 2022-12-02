package it.kimoterru.walls.ui.image

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import it.kimoterru.walls.R
import it.kimoterru.walls.data.remote.models.photo.PhotoResponse
import it.kimoterru.walls.databinding.BottomSheetDownloadBinding
import it.kimoterru.walls.databinding.BottomSheetInfoBinding
import it.kimoterru.walls.databinding.FragmentSelectedImageBinding
import it.kimoterru.walls.util.Constants.Companion.saved
import it.kimoterru.walls.util.Status.ERROR
import it.kimoterru.walls.util.Status.SUCCESS
import it.kimoterru.walls.util.gone
import it.kimoterru.walls.util.showToast
import it.kimoterru.walls.util.visible

@AndroidEntryPoint
class SelectedImageFragment : Fragment(R.layout.fragment_selected_image) {

    private val binding: FragmentSelectedImageBinding by viewBinding()
    private val args: SelectedImageFragmentArgs by navArgs()
    private val viewModel: SelectedImageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        hideFragmentComponent()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPhoto(args.idImage, args.idFavoritePhoto)
    }

    private fun hideFragmentComponent() {
        binding.cardBrush.gone()
        binding.cardDown.gone()
        binding.cardInfo.gone()
    }

    private fun showFragmentComponent() {
        binding.cardBrush.visible()
        binding.cardDown.visible()
        binding.cardInfo.visible()
    }

    private fun initObservers() {
        viewModel.photoLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.visible()
            when (it.status) {
                SUCCESS -> {
                    it.data?.let { it1 -> setImage(it1) }
                    it.data?.let { it1 -> onClick(it1) }
                }
                ERROR -> showToast(it.message)
                else -> {}
            }
        }
    }

    private fun setImage(data: PhotoResponse) {
        Glide.with(binding.selectedImage).load(data.urls?.full)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?, model: Any?, target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    showToast(e?.message)
                    binding.progressBar.visible()
                    hideFragmentComponent()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?, model: Any?, target: Target<Drawable>?,
                    dataSource: DataSource?, isFirstResource: Boolean
                ): Boolean {
                    binding.progressBar.gone()
                    showFragmentComponent()
                    return false
                }
            }).into(binding.selectedImage)
    }

    @SuppressLint("SetTextI18n", "NewApi")
    private fun onClick(data: PhotoResponse) {
        val fileName = data.id + ".jpg"
        binding.cardBrush.setOnClickListener {
            // TODO: 30.12.2021  Добавить возможность установки
            val intent = Intent(Intent.ACTION_SET_WALLPAPER)
            startActivity(Intent.createChooser(intent, "Select Wallpaper")) //Временно

            /*val file = File(Environment.DIRECTORY_PICTURES + "Walls" + fileName)
            val path = Environment.getExternalStorageState(file)
            val intent = Intent(Intent.ACTION_ATTACH_DATA)
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            intent.setDataAndType(Uri.parse(path), "image/jpeg")
            intent.putExtra("mimeType", "image/jpeg")
            this.startActivity(Intent.createChooser(intent, "Set as:"))*/
        }
        binding.cardDown.setOnClickListener {
            val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogApplyToTheme)
            val bindingBottomSheet = BottomSheetDownloadBinding.inflate(layoutInflater)
            dialog.setContentView(bindingBottomSheet.root)

            bindingBottomSheet.let {
                it.saveToDownloads.setOnClickListener {
                    viewModel.downloadPhoto(fileName, data.links?.download!!, requireActivity())
                }
                it.saveToFavorite.setOnClickListener {
                    viewModel.saveToFavorite(data)
                }
                if (args.favoritePhoto == saved) {
                    it.saveToFavorite.gone()
                    it.deleteToFavorites.visible()
                    it.deleteToFavorites.setOnClickListener {
                        viewModel.deleteToFavorites(data)
                    }
                }
            }
            dialog.show()
        }
        binding.cardInfo.setOnClickListener {
            val dialog = BottomSheetDialog(requireContext(), R.style.AppBottomSheetDialogTheme)
            val bindingBottomSheet = BottomSheetInfoBinding.inflate(layoutInflater)
            dialog.setContentView(bindingBottomSheet.root)

            bindingBottomSheet.let {
                //all views in bindingBottomSheet
                Glide.with(it.imageInfoUser).load(args.urlImageUser).into(it.imageInfoUser)
                it.infoUser.text = data.user?.name
                it.infoLocation.text = "${data.location?.city} - ${data.location?.country}"
                it.resolutionInfo.text = "${data.width} x ${data.height}"
                it.createdAtInfo.text = data.createdAt
                it.colorInfo.text = data.color
                it.downInfo.text = data.downloads.toString()
                it.likesInfo.text = data.likes.toString()

                it.makeCam.text = data.exif?.make ?: "null"
                it.modelCam.text = data.exif?.model ?: "null"
                it.exposureTimeCam.text = data.exif?.exposure_time
                it.apertureCam.text = "f/" + data.exif?.aperture
                it.focalLengthCam.text = data.exif?.focal_length + "mm"
                it.isoCam.text = data.exif?.iso.toString()
            }
            dialog.show()
        }
    }
}