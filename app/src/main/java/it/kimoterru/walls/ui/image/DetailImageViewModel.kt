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
import it.kimoterru.walls.domain.models.photo.Photo
import it.kimoterru.walls.domain.usecase.detailI.DeletePhotoUseCase
import it.kimoterru.walls.domain.usecase.detailI.GetPhotoFromApiByIDUseCase
import it.kimoterru.walls.domain.usecase.detailI.GetPhotoFromFavoriteByIDUseCase
import it.kimoterru.walls.domain.usecase.detailI.InsertPhotoUseCase
import it.kimoterru.walls.util.Constants.Companion.CLIENT_ID
import it.kimoterru.walls.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailImageViewModel @Inject constructor(
    private val getPhotoFromFavoriteByIDUseCase: GetPhotoFromFavoriteByIDUseCase,
    private val getPhotoFromApiByIDUseCase: GetPhotoFromApiByIDUseCase,
    private val insertPhotoUseCase: InsertPhotoUseCase,
    private val deletePhotoUseCase: DeletePhotoUseCase
) : ViewModel() {

    private val photoMutableLiveData = MutableLiveData<Resource<Photo>>(Resource.loading())
    val photoLiveData: LiveData<Resource<Photo>> = photoMutableLiveData

    fun getPhoto(id: String, idLocalPhoto: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val photoFromDataBASE = getPhotoFromFavoriteByIDUseCase.invoke(idLocalPhoto)
                if (photoFromDataBASE != null) {
                    delay(700)
                    photoMutableLiveData.postValue(Resource.success(photoFromDataBASE))
                } else {
                    delay(500)
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

    fun saveToFavorite(photo: Photo) = viewModelScope.launch {
        insertPhotoUseCase.invoke(photo)
    }

    fun deleteToFavorites(photo: Photo) = viewModelScope.launch {
        deletePhotoUseCase.invoke(photo)
    }

    fun showButtonDelete(idLocalPhoto: Int?): Boolean {
        return idLocalPhoto != 0
    }
}