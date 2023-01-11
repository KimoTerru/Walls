package it.kimoterru.walls.ui.image

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import it.kimoterru.walls.R
import it.kimoterru.walls.databinding.ActivityDetailImageBinding
import it.kimoterru.walls.databinding.BottomSheetDownloadBinding
import it.kimoterru.walls.databinding.BottomSheetInfoBinding
import it.kimoterru.walls.domain.models.photo.Photo
import it.kimoterru.walls.util.Status.*
import it.kimoterru.walls.util.gone
import it.kimoterru.walls.util.invisible
import it.kimoterru.walls.util.visible

@AndroidEntryPoint
class DetailImageActivity : AppCompatActivity() {

    private val binding: ActivityDetailImageBinding by viewBinding(CreateMethod.INFLATE)
    private val args: DetailImageActivityArgs by navArgs()
    private val viewModel: DetailImageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.getPhoto(args.idNetworkPhoto, args.idLocalPhoto)
        initObservers()
        hideFragmentComponent()
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
        viewModel.photoLiveData.observe(this) {
            when (it.status) {
                SUCCESS -> {
                    it.data?.let { it1 -> setImage(it1) }
                    it.data?.let { it1 -> onClick(it1) }
                }
                ERROR -> {
                    binding.animBar.invisible()
                    binding.textErrorUnderAnim.apply {
                        text = it.message
                        visible()
                    }
                }
                else -> {}
            }
        }
    }

    private fun setImage(data: Photo) {
        Glide.with(binding.detailImage).load(data.urls?.regular)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?, model: Any?, target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    hideFragmentComponent()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?, model: Any?, target: Target<Drawable>?,
                    dataSource: DataSource?, isFirstResource: Boolean
                ): Boolean {
                    binding.animBar.gone()
                    showFragmentComponent()
                    return false
                }
            }).into(binding.detailImage)
    }

    @SuppressLint("SetTextI18n", "NewApi")
    private fun onClick(data: Photo) {
        val fileName = data.id + ".jpg"
        binding.cardBrush.setOnClickListener {
            val intent = Intent(Intent.ACTION_SET_WALLPAPER)
            startActivity(Intent.createChooser(intent, "Select Wallpaper"))

            /*val file = File(Environment.DIRECTORY_PICTURES + "Walls" + fileName)
            val path = Environment.getExternalStorageState(file)
            val intent = Intent(Intent.ACTION_ATTACH_DATA)
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            intent.setDataAndType(Uri.parse(path), "image/jpeg")
            intent.putExtra("mimeType", "image/jpeg")
            this.startActivity(Intent.createChooser(intent, "Set as:"))*/
        }
        binding.cardDown.setOnClickListener {
            val dialog = BottomSheetDialog(this, R.style.BottomSheetDialogApplyToTheme)
            val bindingBottomSheet = BottomSheetDownloadBinding.inflate(layoutInflater)
            dialog.setContentView(bindingBottomSheet.root)

            bindingBottomSheet.let {
                it.saveToDownloads.setOnClickListener {
                    viewModel.downloadPhoto(fileName, data.links?.download!!,this)
                }
                it.saveToFavorite.setOnClickListener {
                    viewModel.saveToFavorite(data)
                }
                if (viewModel.showButtonDelete(args.idLocalPhoto)) {
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
            val dialog = BottomSheetDialog(this, R.style.AppBottomSheetDialogTheme)
            val bindingBottomSheet = BottomSheetInfoBinding.inflate(layoutInflater)
            dialog.setContentView(bindingBottomSheet.root)

            bindingBottomSheet.let {
                //all views in bindingBottomSheet
                Glide.with(it.imageInfoUser).load(args.urlImageUser).placeholder(R.drawable.ic_launcher_foreground).into(it.imageInfoUser)
                it.infoUser.text = data.user?.name
                it.infoLocation.text = "${data.location?.city ?: getText(R.string.unknown)} - ${data.location?.country ?: getText(R.string.unknown)}"
                it.resolutionInfo.text = "${data.width} x ${data.height}"
                it.createdAtInfo.text = data.createdAt
                it.colorInfo.text = data.color
                it.downInfo.text = data.downloads.toString()
                it.likesInfo.text = data.likes.toString()

                it.makeCam.text = data.exif?.make ?: getText(R.string.unknown)
                it.modelCam.text = data.exif?.model ?: getText(R.string.unknown)
                it.exposureTimeCam.text = "${data.exif?.exposure_time ?: getText(R.string.unknown)}s"
                it.apertureCam.text = "f/${data.exif?.aperture ?: getText(R.string.unknown)}"
                it.focalLengthCam.text = "${data.exif?.focal_length ?: getText(R.string.unknown)}mm"
                it.isoCam.text = data.exif?.iso ?: getText(R.string.unknown)
            }
            dialog.show()
        }
    }
}