package it.kimoterru.walls.ui.search

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import it.kimoterru.walls.domain.models.photo.Photo
import it.kimoterru.walls.domain.usecase.search.GetImageSearchUseCase
import it.kimoterru.walls.domain.usecase.search.InsertPhotoUseCase
import it.kimoterru.walls.util.Constants.Companion.jpg
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getImageSearchUseCase: GetImageSearchUseCase,
    private val insertPhotoUseCase: InsertPhotoUseCase
) : ViewModel() {

    suspend fun getImageSearch(
        witchQuery: String,
        query: String,
        color: String,
        order_by: String,
    ): LiveData<PagingData<Photo>> {
        return getImageSearchUseCase.invoke(
            witchQuery, query, color, order_by
        ).cachedIn(viewModelScope)
    }

    fun downloadPhoto(fileName: String, linkDownload: String, requireActivity: FragmentActivity) {
        val filePhoto = fileName + jpg
        val dm: DownloadManager = requireActivity.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(Uri.parse(linkDownload))
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
        request.setTitle(filePhoto)
        request.setDescription("Wait a second...")
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_PICTURES,
            "/Walls/$filePhoto"
        )
        dm.enqueue(request)
    }

    fun saveToFavorite(photo: Photo) = viewModelScope.launch {
        insertPhotoUseCase.invoke(photo)
    }
}