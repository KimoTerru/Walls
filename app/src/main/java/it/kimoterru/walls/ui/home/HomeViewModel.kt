package it.kimoterru.walls.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.kimoterru.walls.data.remote.models.categories.TopicItem
import it.kimoterru.walls.data.remote.models.photo.PhotoItem
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

    val homeResponseLiveData = MutableLiveData<Resource<List<PhotoItem>>>()
    val topicsLiveData = MutableLiveData<Resource<List<TopicItem>>>()

    fun getHomeScreen() {
        homeResponseLiveData.postValue(Resource.loading())
        viewModelScope.launch {
            try {
                val result = getLatestPhotosUseCase.invoke(CLIENT_ID, FIRST_PAGE)
                homeResponseLiveData.postValue(Resource.success(result))
            } catch (e: Exception) {
                e.printStackTrace()
                homeResponseLiveData.postValue(Resource.error(e.message ?: "none"))
            }
        }
    }

    fun getTopics(order: TopicsOrder) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val topicsData = getTopicsUseCase.invoke(
                    CLIENT_ID, FIRST_PAGE, 50, order.query
                )
                topicsLiveData.postValue(Resource.success(topicsData))
            } catch (e: Exception) {
                e.printStackTrace()
                topicsLiveData.postValue(Resource.error(e.message.toString()))
            }
        }
    }
}