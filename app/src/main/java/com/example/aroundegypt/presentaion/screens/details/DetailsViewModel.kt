package com.example.aroundegypt.presentaion.screens.details

import androidx.lifecycle.ViewModel
import com.example.aroundegypt.domain.model.Experience
import com.example.aroundegypt.domain.repository.GetExperienceRepository
import com.example.aroundegypt.domain.repository.LikeExperienceRepository
import com.example.aroundegypt.utilitis.Resources

import javax.inject.Inject

import dagger.hilt.android.lifecycle.HiltViewModel
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
class DetailsViewModel @Inject constructor(
    private val repository: GetExperienceRepository,
    private val repositoryLike: LikeExperienceRepository
) :
    ViewModel() {

    private val _getExperienceDetails: MutableStateFlow<Resources<Experience>> =
        MutableStateFlow(Resources.Loading())
    val getExperienceDetails: StateFlow<Resources<Experience>> = _getExperienceDetails
    private val _likeExperience: MutableStateFlow<Resources<Int>> =
        MutableStateFlow(Resources.Loading())
    val likeExperience: StateFlow<Resources<Int>> = _likeExperience

    private fun coroutineExceptionHandler() = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    private var coroutineScope: CoroutineScope
    private val viewModelJob = Job()

    init {
        coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)
    }


    fun getDetails(id: String) {
        coroutineScope.launch(coroutineExceptionHandler()) {
            repository(id).collectLatest {
                _getExperienceDetails.value = it
            }
        }
    }

    fun likeExperience(id: String) {
        coroutineScope.launch(coroutineExceptionHandler()) {
            repositoryLike(id).collectLatest {
                    _likeExperience.value = it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        coroutineScope.cancel()
    }


}