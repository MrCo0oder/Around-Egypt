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
    private val experienceRepository: ExperienceRepository,
    private val likeExperienceRepository: LikeExperienceRepository,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    //region StateFlows
    private val _recommendedExperiences =
        MutableStateFlow<Resources<List<Experience>>>(Resources.Loading())
    val recommendedExperiencesState: StateFlow<Resources<List<Experience>>> =
        _recommendedExperiences.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Resources.Loading()
        )

    private val _mostRecentExperiences = MutableStateFlow<Resources<List<Experience>>>(Resources.Loading())
    val mostRecentExperiencesState: StateFlow<Resources<List<Experience>>> =
        _mostRecentExperiences.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Resources.Loading()
        )

    private val _filteredExperiences =
        MutableStateFlow<Resources<List<Experience>>>(Resources.Loading())
    val filteredExperiencesState: StateFlow<Resources<List<Experience>>> =
        _filteredExperiences.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Resources.Loading()
        )

    private val _likeExperienceState: MutableStateFlow<Resources<Int>> =
        MutableStateFlow(Resources.Loading())
    val likeExperienceState: StateFlow<Resources<Int>> = _likeExperienceState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Resources.Loading()
    )
    //endregion

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    init {
        refreshData()
    }

    private fun refreshData() {
        getRecommendedList()
        getMostRecentList()
    }

    fun getRecommendedList() {
        fetchData(_recommendedExperiences) { experienceRepository.getRecommendedList() }
    }

    fun getMostRecentList() {
        fetchData(_mostRecentExperiences) { experienceRepository.getMostRecentList() }
    }

    fun getFilteredList(filterQuery: String) {
        fetchData(_filteredExperiences) { experienceRepository.getFilteredList(filterQuery) }
    }

    fun likeExperience(experienceId: String) {
        viewModelScope.launch(exceptionHandler + mainDispatcher) {
            likeExperienceRepository(experienceId).collectLatest { result ->
                _likeExperienceState.value = result
                if (result is Resources.Success) {
                    updateItem(experienceId)
                }
            }
        }
    }

    fun clearSearch() {
        _filteredExperiences.value = Resources.Success(emptyList())
    }

    //region Private Functions
    private fun fetchData(
        stateFlow: MutableStateFlow<Resources<List<Experience>>>,
        fetch: suspend () -> Flow<Resources<List<Experience>>>
    ) {
        viewModelScope.launch(exceptionHandler + mainDispatcher) {
            fetch().collectLatest { stateFlow.value = it }
        }
    }

    private fun updateItem(experienceId: String) {
        updateExperienceList(_recommendedExperiences, experienceId)
        updateExperienceList(_mostRecentExperiences, experienceId)
    }

    private fun updateExperienceList(
        stateFlow: MutableStateFlow<Resources<List<Experience>>>,
        experienceId: String
    ) {
        stateFlow.update { currentResource ->
            if (currentResource is Resources.Success) {
                val updatedList = currentResource.data?.map { experience ->
                    if (experience.id == experienceId) {
                        experience.copy(isLiked = true, likesNo = experience.likesNo + 1)
                    } else {
                        experience
                    }
                }
                Resources.Success(updatedList)
            } else {
                currentResource
            }
        }
    }
    //endregion
}