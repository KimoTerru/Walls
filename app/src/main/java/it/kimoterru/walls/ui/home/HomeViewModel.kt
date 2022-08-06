package it.kimoterru.walls.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.kimoterru.walls.data.remote.models.photo.PhotoResponse
import it.kimoterru.walls.data.remote.models.topic.TopicResponse
import it.kimoterru.walls.domain.usecase.home.GetLatestPhotosUseCase
import it.kimoterru.walls.domain.usecase.home.GetTopicsUseCase
import it.kimoterru.walls.util.Constants.Companion.CLIENT_ID
import it.kimoterru.walls.util.Constants.Companion.FIRST_PAGE
import it.kimoterru.walls.util.Resource
import it.kimoterru.walls.util.TopicsOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLatestPhotosUseCase: GetLatestPhotosUseCase,
    private val getTopicsUseCase: GetTopicsUseCase
) : ViewModel() {

    private val homeResponseMutableLiveData = MutableLiveData<Resource<List<PhotoResponse>>>()
    val homeResponseLiveData: LiveData<Resource<List<PhotoResponse>>> = homeResponseMutableLiveData

    private val topicsMutableLiveData = MutableLiveData<Resource<List<TopicResponse>>>()
    val topicsLiveData: LiveData<Resource<List<TopicResponse>>> = topicsMutableLiveData

    fun getHomeScreen() {
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

    fun getTopics(order: TopicsOrder) {
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