package it.kimoterru.walls.ui.color

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.kimoterru.walls.models.search.SearchItem
import it.kimoterru.walls.repo.WallpaperRepository
import it.kimoterru.walls.util.Constants
import it.kimoterru.walls.util.Resource
import it.kimoterru.walls.util.TopicsOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColorsViewModel @Inject constructor(private val repository: WallpaperRepository) :
    ViewModel() {
    val imageColorLiveData = MutableLiveData<Resource<SearchItem>>()

    fun getImageColors(query: String, color: String, order: TopicsOrder) {
        imageColorLiveData.postValue(Resource.loading())
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.getImageColors(
                    query, color, Constants.CLIENT_ID, 30, order.query
                )
                imageColorLiveData.postValue(Resource.success(result))
            } catch (e: Exception) {
                e.printStackTrace()
                imageColorLiveData.postValue(Resource.error(e.message ?: "none"))
            }
        }
    }
}