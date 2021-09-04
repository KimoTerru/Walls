package it.kimoterru.walls.ui.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.kimoterru.walls.models.photo.PhotoItem
import it.kimoterru.walls.repo.WallpaperRepository
import it.kimoterru.walls.util.Constants
import it.kimoterru.walls.util.Resource
import it.kimoterru.walls.util.TopicsOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: WallpaperRepository) :
    ViewModel() {
    val imageTopicsLiveData = MutableLiveData<Resource<List<PhotoItem>>>()

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
}