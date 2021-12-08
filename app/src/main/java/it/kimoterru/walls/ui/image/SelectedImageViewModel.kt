package it.kimoterru.walls.ui.image

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.kimoterru.walls.models.photo.PhotoItem
import it.kimoterru.walls.repo.WallpaperRepository
import it.kimoterru.walls.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectedImageViewModel @Inject constructor(private val repository: WallpaperRepository) :
    ViewModel() {
    val photoLiveData = MutableLiveData<PhotoItem>()

    fun getPhoto(id: String, id_photo: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val photoDataBASE = repository.getPhotoFromFavoriteByID(id_photo)
                if (photoDataBASE != null) {
                    photoLiveData.postValue(photoDataBASE!!)
                } else {
                    val photoDataAPI = repository.getPhoto(id, Constants.CLIENT_ID)
                    photoLiveData.postValue(photoDataAPI)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}