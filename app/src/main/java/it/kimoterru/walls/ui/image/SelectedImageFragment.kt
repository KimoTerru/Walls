package it.kimoterru.walls.ui.image

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import it.kimoterru.walls.R
import it.kimoterru.walls.database.PhotoDao
import it.kimoterru.walls.databinding.BottomSheetDownloadBinding
import it.kimoterru.walls.databinding.BottomSheetInfoBinding
import it.kimoterru.walls.databinding.FragmentSelectedImageBinding
import it.kimoterru.walls.models.photo.PhotoItem
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SelectedImageFragment : Fragment(R.layout.fragment_selected_image) {
    private var _binding: FragmentSelectedImageBinding? = null
    private val binding get() = _binding!!

    private val args: SelectedImageFragmentArgs by navArgs()
    private val viewModel: SelectedImageViewModel by viewModels()

    @Inject
    lateinit var photoDataBase: PhotoDao

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
        viewModel.getPhoto(args.idImage, args.idFavoritePhoto)
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
        val fileName = data.id + ".jpg"
        binding.cardBrush.setOnClickListener {
            // TODO: 30.11.2021  Добавить возможность установки
            val intent = Intent(Intent.ACTION_SET_WALLPAPER)
            startActivity(Intent.createChooser(intent, "Select Wallpaper")) //Временно
            /*val intent = Intent(Intent.ACTION_ATTACH_DATA)
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            intent.setDataAndType(uri, "image/jpeg")
            intent.putExtra("mimeType", "image/jpeg")
            this.startActivity(Intent.createChooser(intent, "Set as:"))*/
        }
        binding.cardDown.setOnClickListener {
            val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogApplyToTheme)
            val bindingBottomSheet = BottomSheetDownloadBinding.inflate(layoutInflater)
            dialog.setContentView(bindingBottomSheet.root)

            bindingBottomSheet.let {
                it.saveToDownloads.setOnClickListener {
                    val dm: DownloadManager =
                        requireActivity().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
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
                it.saveToFavorite.setOnClickListener {
                    lifecycleScope.launch {
                        photoDataBase.insertPhoto(data)
                    }
                }
                if (args.favoritePhoto == 2) {
                    it.saveToFavorite.visibility = View.GONE
                    it.deleteToFavorites.visibility = View.VISIBLE
                    it.deleteToFavorites.setOnClickListener {
                        lifecycleScope.launch {
                            photoDataBase.deletePhoto(data)
                        }
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
                it.infoUser.text = data.user.name
                it.resolutionInfo.text = "${data.width}x${data.height}"
                it.createdAtInfo.text = data.createdAt
                it.colorInfo.text = data.color
                it.downInfo.text = data.downloads.toString()
            }
            dialog.show()
        }
    }
}