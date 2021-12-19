package it.kimoterru.walls.ui.image

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.kimoterru.walls.data.models.photo.PhotoItem
import it.kimoterru.walls.data.repository.WallpaperRepository
import it.kimoterru.walls.util.Constants
import it.kimoterru.walls.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectedImageViewModel @Inject constructor(private val repository: WallpaperRepository) :
    ViewModel() {
    val photoLiveData = MutableLiveData<Resource<PhotoItem>>()

    fun getPhoto(id: String, id_photo: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val photoDataBASE = repository.getPhotoFromFavoriteByID(id_photo)
                if (photoDataBASE != null) {
                    photoLiveData.postValue(Resource.success(photoDataBASE))
                } else {
                    val photoDataAPI = repository.getPhoto(id, Constants.CLIENT_ID)
                    photoLiveData.postValue(Resource.success(photoDataAPI))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                photoLiveData.postValue(Resource.error(e.message ?: "none"))
            }
        }
    }

    fun downloadPhoto(fileName: String, linkDownload: String, requireActivity: FragmentActivity) {
        val dm: DownloadManager =
            requireActivity.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(Uri.parse(linkDownload))
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
        request.setTitle(fileName)
        request.setDescription("Wait a second...")
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, "/Walls/$fileName")
        dm.enqueue(request)
    }

    fun saveToFavorite(data: PhotoItem) {
        viewModelScope.launch {
            repository.insertPhoto(data)
        }
    }

    fun deleteToFavorites(data: PhotoItem) {
        viewModelScope.launch {
            repository.deletePhoto(data)
        }
    }
}