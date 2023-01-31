package it.kimoterru.walls.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.kimoterru.walls.domain.models.photo.Photo
import it.kimoterru.walls.domain.models.topic.Topic
import it.kimoterru.walls.domain.usecase.home.GetLatestPhotosUseCase
import it.kimoterru.walls.domain.usecase.home.GetTopicsUseCase
import it.kimoterru.walls.util.Constants.Companion.CLIENT_ID
import it.kimoterru.walls.util.Constants.Companion.FIRST_PAGE
import it.kimoterru.walls.util.Resource
import it.kimoterru.walls.util.TopicsOrder
import it.kimoterru.walls.util.TopicsOrder.POSITION
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLatestPhotosUseCase: GetLatestPhotosUseCase,
    private val getTopicsUseCase: GetTopicsUseCase
) : ViewModel() {

    private val homeResponseMutableLiveData = MutableLiveData<Resource<List<Photo>>>()
    val homeResponseLiveData: LiveData<Resource<List<Photo>>> = homeResponseMutableLiveData

    private val topicsMutableLiveData = MutableLiveData<Resource<List<Topic>>>()
    val topicsLiveData: LiveData<Resource<List<Topic>>> = topicsMutableLiveData

    init {
        getHomeScreen()
        getTopics(POSITION)
    }

    fun updateData() {
        getHomeScreen()
        getTopics(POSITION)
    }

    private fun getHomeScreen() {
        homeResponseMutableLiveData.postValue(Resource.loading())
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = getLatestPhotosUseCase.invoke(CLIENT_ID, FIRST_PAGE)
                homeResponseMutableLiveData.postValue(Resource.success(result))
            } catch (e: Exception) {
                e.printStackTrace()
                homeResponseMutableLiveData.postValue(Resource.error(e.message ?: "none"))
            }
        }
    }

    private fun getTopics(order: TopicsOrder) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val topicsData = getTopicsUseCase.invoke(
                    CLIENT_ID, FIRST_PAGE, 50, order.query
                )
                topicsMutableLiveData.postValue(Resource.success(topicsData))
            } catch (e: Exception) {
                e.printStackTrace()
                topicsMutableLiveData.postValue(Resource.error(e.message.toString()))
            }
        }
    }
}