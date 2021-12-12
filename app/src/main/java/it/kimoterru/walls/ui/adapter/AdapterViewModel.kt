package it.kimoterru.walls.ui.adapter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.kimoterru.walls.data.models.photo.PhotoItem
import it.kimoterru.walls.data.models.search.SearchItem
import it.kimoterru.walls.data.repository.WallpaperRepository
import it.kimoterru.walls.util.Constants
import it.kimoterru.walls.util.Resource
import it.kimoterru.walls.util.TopicsOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdapterViewModel @Inject constructor(private val repository: WallpaperRepository) :
    ViewModel() {
    val imageTopicsLiveData = MutableLiveData<Resource<List<PhotoItem>>>()
    val imageLiveData = MutableLiveData<Resource<SearchItem>>()

    fun getImageTopics(id_or_slug: String, order: TopicsOrder) {
        imageTopicsLiveData.postValue(Resource.loading())
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.getImageTopics(
                    id_or_slug, Constants.CLIENT_ID, 30, order.query
                )
                imageTopicsLiveData.postValue(Resource.success(result))
            } catch (e: Exception) {
                e.printStackTrace()
                imageTopicsLiveData.postValue(Resource.error(e.message ?: "none"))
            }
        }
    }

    fun getImageColors(query: String, color: String, order: TopicsOrder) {
        imageLiveData.postValue(Resource.loading())
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.getImageColors(
                    query, color, Constants.CLIENT_ID, 30, order.query
                )
                imageLiveData.postValue(Resource.success(result))
            } catch (e: Exception) {
                e.printStackTrace()
                imageLiveData.postValue(Resource.error(e.message ?: "none"))
            }
        }
    }

    fun getImageSearch(query: String, order: TopicsOrder) {
        imageLiveData.postValue(Resource.loading())
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.getImageSearch(
                    query, Constants.CLIENT_ID, 30, order.query
                )
                imageLiveData.postValue(Resource.success(result))
            } catch (e: Exception) {
                e.printStackTrace()
                imageLiveData.postValue(Resource.error(e.message ?: "none"))
            }
        }
    }
}