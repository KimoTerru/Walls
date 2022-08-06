package it.kimoterru.walls.ui.image

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.kimoterru.walls.data.remote.models.photo.PhotoResponse
import it.kimoterru.walls.domain.usecase.detailI.DeletePhotoUseCase
import it.kimoterru.walls.domain.usecase.detailI.GetPhotoFromApiByIDUseCase
import it.kimoterru.walls.domain.usecase.detailI.GetPhotoFromFavoriteByIDUseCase
import it.kimoterru.walls.domain.usecase.detailI.InsertPhotoUseCase
import it.kimoterru.walls.util.Constants.Companion.CLIENT_ID
import it.kimoterru.walls.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectedImageViewModel @Inject constructor(
    private val getPhotoFromFavoriteByIDUseCase: GetPhotoFromFavoriteByIDUseCase,
    private val getPhotoFromApiByIDUseCase: GetPhotoFromApiByIDUseCase,
    private val insertPhotoUseCase: InsertPhotoUseCase,
    private val deletePhotoUseCase: DeletePhotoUseCase
) : ViewModel() {

    private val photoMutableLiveData = MutableLiveData<Resource<PhotoResponse>>()
    val photoLiveData: LiveData<Resource<PhotoResponse>> = photoMutableLiveData

    fun getPhoto(id: String, id_photo: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val photoDataBASE = getPhotoFromFavoriteByIDUseCase.invoke(id_photo)
                if (photoDataBASE != null) {
                    photoMutableLiveData.postValue(Resource.success(photoDataBASE))
                } else {
                    val photoDataAPI = getPhotoFromApiByIDUseCase.invoke(id, CLIENT_ID)
                    photoMutableLiveData.postValue(Resource.success(photoDataAPI))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                photoMutableLiveData.postValue(Resource.error(e.message ?: "none"))
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
        request.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_PICTURES,
            "/Walls/$fileName"
        )
        dm.enqueue(request)
    }

    fun saveToFavorite(photo: PhotoResponse) {
        viewModelScope.launch {
            insertPhotoUseCase.invoke(photo)
        }
    }

    fun deleteToFavorites(photo: PhotoResponse) {
        viewModelScope.launch {
            deletePhotoUseCase.invoke(photo)
        }
    }
}