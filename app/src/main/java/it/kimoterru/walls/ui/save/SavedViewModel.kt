package it.kimoterru.walls.ui.save

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.kimoterru.walls.data.remote.models.photo.PhotoItem
import it.kimoterru.walls.domain.usecase.save.GetAllPhotosFromFavoriteUseCase
import it.kimoterru.walls.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val getAllPhotosFromFavoriteUseCase: GetAllPhotosFromFavoriteUseCase
) : ViewModel() {

    val photoLiveData = MutableLiveData<Resource<List<PhotoItem>>>()

    fun getAllPhotosFromFavorite() {
        photoLiveData.postValue(Resource.loading())
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val photoData = getAllPhotosFromFavoriteUseCase.invoke()
                if (photoData.isNotEmpty()) {
                    photoLiveData.postValue(Resource.success(photoData))
                } else {
                    photoLiveData.postValue(Resource.error("Empty!"))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                photoLiveData.postValue(Resource.error(e.message ?: "none"))
            }
        }
    }
}