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

    fun getPhoto(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val photoData = repository.getPhoto(id, Constants.CLIENT_ID)
                photoLiveData.postValue(photoData)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}