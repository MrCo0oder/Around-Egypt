package com.example.aroundegypt.presentaion.screens.home;

import androidx.lifecycle.ViewModel;
import com.example.aroundegypt.domain.model.Experience
import com.example.aroundegypt.domain.repository.ExperienceRepository
import com.example.aroundegypt.utilitis.Resources

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: ExperienceRepository) :
    ViewModel() {

    private val _recommendedList: MutableStateFlow<Resources<List<Experience>>> =
        MutableStateFlow(Resources.Loading())
    val recommendedList: StateFlow<Resources<List<Experience>>> = _recommendedList

    private fun coroutineExceptionHandler() = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    private var coroutineScope: CoroutineScope
    private val viewModelJob = Job()

    init {
        coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)
    }


    fun getRecommendedList() {
        coroutineScope.launch(coroutineExceptionHandler()) {
            repository.getRecommendedList().collectLatest {
                _recommendedList.value = it
            }
        }
    }

    private val _mostRecentList: MutableStateFlow<Resources<List<Experience>>> =
        MutableStateFlow(Resources.Loading())
    val mostRecentList: StateFlow<Resources<List<Experience>>> = _mostRecentList
    fun getMostRecentList() {
        coroutineScope.launch(coroutineExceptionHandler()) {
            repository.getMostRecentList().collectLatest {
                _mostRecentList.value = it
            }
        }
    }

    private val _filterList: MutableStateFlow<Resources<List<Experience>>> =
        MutableStateFlow(Resources.Loading())
    val filterList: StateFlow<Resources<List<Experience>>> = _filterList
    fun getFilteredList(query: String) {
        coroutineScope.launch(coroutineExceptionHandler()) {
            repository.getFilteredList(query).collectLatest {
                _filterList.value = it
            }
        }
    }

    fun clearSearch() {
        _filterList.value = Resources.Success(emptyList())
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        coroutineScope.cancel()
    }


}