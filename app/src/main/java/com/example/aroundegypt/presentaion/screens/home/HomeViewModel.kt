package com.example.aroundegypt.presentaion.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aroundegypt.data.di.MainDispatcher
import com.example.aroundegypt.domain.model.Experience
import com.example.aroundegypt.domain.repository.ExperienceRepository
import com.example.aroundegypt.domain.repository.LikeExperienceRepository
import com.example.aroundegypt.utilitis.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ExperienceRepository,
    private val likeRepository: LikeExperienceRepository,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _recommendedExperiences =
        MutableStateFlow<Resources<List<Experience>>>(Resources.Loading())
    val recommendedExperiencesState: StateFlow<Resources<List<Experience>>> =
        _recommendedExperiences.asStateFlow()

    private val _mostRecentList = MutableStateFlow<Resources<List<Experience>>>(Resources.Loading())
    val mostRecentExperiencesState: StateFlow<Resources<List<Experience>>> =
        _mostRecentList.asStateFlow()

    private val _filteredExperiences =
        MutableStateFlow<Resources<List<Experience>>>(Resources.Loading())
    val filteredExperiencesState: StateFlow<Resources<List<Experience>>> =
        _filteredExperiences.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    init {
        viewModelScope.launch(exceptionHandler + dispatcher) {
            repository.getRecommendedList()
                .collectLatest { _recommendedExperiences.value = it }

        }
        viewModelScope.launch(exceptionHandler + dispatcher) {
            repository.getMostRecentList()
                .collectLatest { _mostRecentList.value = it }
        }
    }

    fun getRecommendedList() {
        viewModelScope.launch(exceptionHandler + dispatcher) {
            repository.getRecommendedList()
                .collectLatest { _recommendedExperiences.value = it }
        }
    }

    fun getMostRecentList() {
        viewModelScope.launch(exceptionHandler + dispatcher) {
            repository.getMostRecentList()
                .collectLatest { _mostRecentList.value = it }
        }
    }

    fun getFilteredList(filterQuery: String) {
        viewModelScope.launch(exceptionHandler + dispatcher) {
            repository.getFilteredList(filterQuery)
                .collectLatest { _filteredExperiences.value = it }
        }
    }

    private val _likeExperienceState: MutableStateFlow<Resources<Int>> =
        MutableStateFlow(Resources.Loading())
    val likeExperienceState: StateFlow<Resources<Int>> get() = _likeExperienceState

    fun likeExperience(experienceId: String) {
        viewModelScope.launch(exceptionHandler + dispatcher) {
            likeRepository(experienceId,).collectLatest {
                when (it) {
                    is Resources.Success -> {
                        updateItem(experienceId)
                    }

                    is Resources.Error -> {
                    }

                    else -> Unit
                }
            }
        }
    }

    private fun updateItem(experienceId: String) {
        val recommendedList = _recommendedExperiences.value.data?.toMutableList().apply {
            this?.find { it.id == experienceId }?.copy(
                isLiked = true,
                likesNo = _recommendedExperiences.value.data?.find { it.id == experienceId }?.likesNo!!.plus(
                    1
                )
            )?.let { this[this.indexOf(this.find { i -> i.id == experienceId })] = it }
        }

        _recommendedExperiences.value = Resources.Success(recommendedList!!)
        val mostRecentList = _mostRecentList.value.data?.toMutableList().apply {
            this?.find { it.id == experienceId }?.copy(
                isLiked = true,
                likesNo = _mostRecentList.value.data?.find { it.id == experienceId }?.likesNo!!.plus(
                    1
                )
            )?.let { this[this.indexOf(this.find { i -> i.id == experienceId })] = it }
        }
        _mostRecentList.value = Resources.Success(mostRecentList!!)
    }


    fun clearSearch() {
        _filteredExperiences.value = Resources.Success(emptyList())
    }
}
