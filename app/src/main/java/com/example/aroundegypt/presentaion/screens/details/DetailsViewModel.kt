package com.example.aroundegypt.presentaion.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aroundegypt.domain.model.Experience
import com.example.aroundegypt.domain.repository.GetExperienceRepository
import com.example.aroundegypt.domain.repository.LikeExperienceRepository
import com.example.aroundegypt.utilitis.Resources
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val experienceRepository: GetExperienceRepository,
    private val likeRepository: LikeExperienceRepository
) : ViewModel() {

    private val _experienceDetailsState: MutableStateFlow<Resources<Experience>> = MutableStateFlow(Resources.Loading())
    val experienceDetailsState: StateFlow<Resources<Experience>> get() = _experienceDetailsState

    private val _likeExperienceState: MutableStateFlow<Resources<Int>> = MutableStateFlow(Resources.Loading())
    val likeExperienceState: StateFlow<Resources<Int>> get() = _likeExperienceState

    fun getDetails(id: String) {
        viewModelScope.launch(handleError()) {
            experienceRepository(id).collectLatest {
                _experienceDetailsState.value = it
            }
        }
    }

    fun likeExperience(id: String) {
        viewModelScope.launch(handleError()) {
            likeRepository(id).collectLatest {
                _likeExperienceState.value = it
            }
        }
    }

    private fun handleError(): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }
    }
}