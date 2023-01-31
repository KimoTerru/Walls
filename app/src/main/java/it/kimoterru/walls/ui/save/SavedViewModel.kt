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

    fun getAllPhotosFromFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val photoData = getAllPhotosFromFavoriteUseCase.invoke()
                if (photoData.isNotEmpty()) {
                    photoMutableLiveData.postValue(Resource.success(photoData))
                } else {
                    photoMutableLiveData.postValue(Resource.error("none"))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                photoMutableLiveData.postValue(Resource.error(e.message ?: "none"))
            }
        }
    }

    fun deletePhotoFromDataBase(photo: Photo) = viewModelScope.launch {
        deletePhotoUseCase.invoke(photo)
    }
}