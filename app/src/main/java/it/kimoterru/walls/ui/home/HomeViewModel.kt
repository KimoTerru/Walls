package it.kimoterru.walls.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.kimoterru.walls.model.unsplash.PhotoItem
import it.kimoterru.walls.repo.WallpaperRepository
import it.kimoterru.walls.util.Constants
import it.kimoterru.walls.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: WallpaperRepository) : ViewModel() {
    val homeResponseLiveData = MutableLiveData<Resource<List<PhotoItem>>>()

    fun getHomeScreen() {
        homeResponseLiveData.postValue(Resource.loading())
        viewModelScope.launch {
            try {
                val result = repository.getLatestPhotos(Constants.CLIENT_ID, 1)
                homeResponseLiveData.postValue(Resource.success(result))
            } catch (e: Exception) {
                e.printStackTrace()
                homeResponseLiveData.postValue(Resource.error(e.message ?: "none"))
            }
        }
    }
}