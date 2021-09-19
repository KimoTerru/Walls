package it.kimoterru.walls.ui.search

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
class SearchViewModel @Inject constructor(private val repository: WallpaperRepository) :
    ViewModel() {
    val imageLiveData = MutableLiveData<Resource<SearchItem>>()

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