package it.kimoterru.walls.ui.save

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.kimoterru.walls.domain.models.photo.Photo
import it.kimoterru.walls.domain.usecase.save.DeletePhotoUseCase
import it.kimoterru.walls.domain.usecase.save.GetAllPhotosFromFavoriteUseCase
import it.kimoterru.walls.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val getAllPhotosFromFavoriteUseCase: GetAllPhotosFromFavoriteUseCase,
    private val deletePhotoUseCase: DeletePhotoUseCase
) : ViewModel() {

    private val photoMutableLiveData = MutableLiveData<Resource<List<Photo>>>(Resource.loading())
    val photoLiveData: LiveData<Resource<List<Photo>>> = photoMutableLiveData

    init {
        getAllPhotosFromFavorite()
    }

    private fun getAllPhotosFromFavorite() = viewModelScope.launch(Dispatchers.IO) {
        val photoData = getAllPhotosFromFavoriteUseCase.invoke()
        photoMutableLiveData.postValue(photoData)
    }

    fun updateAllPhotosFromFavorite() {
        getAllPhotosFromFavorite()
    }

    fun deletePhotoFromDataBase(photo: Photo) = viewModelScope.launch {
        deletePhotoUseCase.invoke(photo)
    }
}