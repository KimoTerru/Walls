package it.kimoterru.walls.ui.search

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import it.kimoterru.walls.domain.models.photo.Photo
import it.kimoterru.walls.domain.models.search.SearchPhoto
import it.kimoterru.walls.domain.usecase.search.GetImageColorsUseCase
import it.kimoterru.walls.domain.usecase.search.GetImageSearchUseCase
import it.kimoterru.walls.domain.usecase.search.GetImageTopicsUseCase
import it.kimoterru.walls.util.Constants.Companion.CLIENT_ID
import it.kimoterru.walls.util.Constants.Companion.PER_PAGE
import it.kimoterru.walls.util.Constants.Companion.colors
import it.kimoterru.walls.util.Constants.Companion.search
import it.kimoterru.walls.util.Constants.Companion.topics
import it.kimoterru.walls.util.Resource
import it.kimoterru.walls.util.TopicsOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getImageTopicsUseCase: GetImageTopicsUseCase,
    private val getImageColorsUseCase: GetImageColorsUseCase,
    private val getImageSearchUseCase: GetImageSearchUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val imageTopicsMutableLiveData = MutableLiveData<Resource<List<Photo>>>()
    val imageTopicsLiveData: LiveData<Resource<List<Photo>>> = imageTopicsMutableLiveData

    private val imageSearchMutableLiveData = MutableLiveData<Resource<SearchPhoto>>()
    val imageSearchLiveData: LiveData<Resource<SearchPhoto>> = imageSearchMutableLiveData

    private val fragment = savedStateHandle.get<String>("whichSnippet")
    private val query = savedStateHandle.get<String>("query")

    init {
        if (fragment!!.isNotEmpty() and query!!.isNotEmpty()) {
            whichSnippet(fragment.toString(), query.toString())
        }
    }

    var isLoading = false
    var isLastPage = false
    var pagePhoto = 1

    private fun whichSnippet(fragment: String, query: String) {
        when (fragment) { // Determine which request
            topics -> getImageTopics(query, TopicsOrder.LATEST)
            colors -> getImageColors(query, query, TopicsOrder.LATEST)
            search -> getImageSearch(query, TopicsOrder.LATEST)
        }
    }

    fun updateDate() {
        whichSnippet(fragment.toString(), query.toString())
    }

    fun addData() {
        whichSnippet(fragment.toString(), query.toString())
    }

    private fun getImageTopics(id_or_slug: String, order: TopicsOrder) {
        imageTopicsMutableLiveData.postValue(Resource.loading())
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = getImageTopicsUseCase.invoke(
                    id_or_slug, CLIENT_ID, pagePhoto, PER_PAGE, order.query
                )
                imageTopicsMutableLiveData.postValue(Resource.success(result))
            } catch (e: Exception) {
                e.printStackTrace()
                imageTopicsMutableLiveData.postValue(Resource.error(e.message ?: "none"))
            }
        }
    }

    private fun getImageColors(query: String, color: String, order: TopicsOrder) {
        imageSearchMutableLiveData.postValue(Resource.loading())
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = getImageColorsUseCase.invoke(
                    query, color, CLIENT_ID, pagePhoto, PER_PAGE, order.query
                )
                imageSearchMutableLiveData.postValue(Resource.success(result))
            } catch (e: Exception) {
                e.printStackTrace()
                imageSearchMutableLiveData.postValue(Resource.error(e.message ?: "none"))
            }
        }
    }

    private fun getImageSearch(query: String, order: TopicsOrder) {
        imageSearchMutableLiveData.postValue(Resource.loading())
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = getImageSearchUseCase.invoke(
                    query, CLIENT_ID, pagePhoto, PER_PAGE, order.query
                )
                imageSearchMutableLiveData.postValue(Resource.success(result))
            } catch (e: Exception) {
                e.printStackTrace()
                imageSearchMutableLiveData.postValue(Resource.error(e.message ?: "none"))
            }
        }
    }
}