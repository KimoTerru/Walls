package it.kimoterru.walls.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.kimoterru.walls.data.remote.models.photo.PhotoItem
import it.kimoterru.walls.data.remote.models.search.SearchItem
import it.kimoterru.walls.domain.usecase.search.GetImageColorsUseCase
import it.kimoterru.walls.domain.usecase.search.GetImageSearchUseCase
import it.kimoterru.walls.domain.usecase.search.GetImageTopicsUseCase
import it.kimoterru.walls.util.Constants.Companion.CLIENT_ID
import it.kimoterru.walls.util.Constants.Companion.PER_PAGE
import it.kimoterru.walls.util.Resource
import it.kimoterru.walls.util.TopicsOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getImageTopicsUseCase: GetImageTopicsUseCase,
    private val getImageColorsUseCase: GetImageColorsUseCase,
    private val getImageSearchUseCase: GetImageSearchUseCase
) : ViewModel() {

    val imageTopicsLiveData = MutableLiveData<Resource<List<PhotoItem>>>()
    val imageLiveData = MutableLiveData<Resource<SearchItem>>()

    var isLoading = false
    var isLastPage = false
    var pagePhoto = 1

    fun whichSnippet(fragment: Int, query: String) {
        when (fragment) { // Determine which request
            1 -> getImageTopics(query, TopicsOrder.LATEST)
            2 -> getImageColors(query, query, TopicsOrder.LATEST)
            3 -> getImageSearch(query, TopicsOrder.LATEST)
        }
    }

    private fun getImageTopics(id_or_slug: String, order: TopicsOrder) {
        imageTopicsLiveData.postValue(Resource.loading())
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = getImageTopicsUseCase.invoke(
                    id_or_slug, CLIENT_ID, pagePhoto, PER_PAGE, order.query
                )
                imageTopicsLiveData.postValue(Resource.success(result))
            } catch (e: Exception) {
                e.printStackTrace()
                imageTopicsLiveData.postValue(Resource.error(e.message ?: "none"))
            }
        }
    }

    private fun getImageColors(query: String, color: String, order: TopicsOrder) {
        imageLiveData.postValue(Resource.loading())
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = getImageColorsUseCase.invoke(
                    query, color, CLIENT_ID, pagePhoto, PER_PAGE, order.query
                )
                imageLiveData.postValue(Resource.success(result))
            } catch (e: Exception) {
                e.printStackTrace()
                imageLiveData.postValue(Resource.error(e.message ?: "none"))
            }
        }
    }

    private fun getImageSearch(query: String, order: TopicsOrder) {
        imageLiveData.postValue(Resource.loading())
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = getImageSearchUseCase.invoke(
                    query, CLIENT_ID, pagePhoto, PER_PAGE, order.query
                )
                imageLiveData.postValue(Resource.success(result))
            } catch (e: Exception) {
                e.printStackTrace()
                imageLiveData.postValue(Resource.error(e.message ?: "none"))
            }
        }
    }
}