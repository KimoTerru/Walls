package it.kimoterru.walls.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.kimoterru.walls.model.Wallpaper
import it.kimoterru.walls.repo.WallpaperRepository
import it.kimoterru.walls.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: WallpaperRepository) : ViewModel() {
    val homeResponseLiveData = MutableLiveData<Resource<Wallpaper>>()

    fun getHomeScreen() {
        homeResponseLiveData.postValue(Resource.loading())
        viewModelScope.launch {
            try {
                val result = repository.getTopWallpapers("Anime", "0", "1d", "toplist", "asc", 1)
                homeResponseLiveData.postValue(Resource.success(result))
            } catch (e: Exception) {
                e.printStackTrace()
                homeResponseLiveData.postValue(Resource.error(e.message ?: "none"))
            }
        }
    }
}