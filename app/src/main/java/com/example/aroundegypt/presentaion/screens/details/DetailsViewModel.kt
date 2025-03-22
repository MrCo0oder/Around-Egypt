package com.example.aroundegypt.presentaion.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aroundegypt.domain.model.Experience
import com.example.aroundegypt.domain.repository.GetExperienceRepository
import com.example.aroundegypt.utilitis.Resources
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val experienceRepository: GetExperienceRepository,
) : ViewModel() {

    private val _experienceDetailsState: MutableStateFlow<Resources<Experience>> =
        MutableStateFlow(Resources.Loading())
    val experienceDetailsState: StateFlow<Resources<Experience>> = _experienceDetailsState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Resources.Loading()
    )


    fun getDetails(id: String) {
        _experienceDetailsState.value = Resources.Loading()
        viewModelScope.launch(handleError()) {
            experienceRepository(id).collectLatest { result ->
                _experienceDetailsState.update { result }
            }
        }
    }

    private fun handleError(): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, _ ->
            _experienceDetailsState.update { Resources.Error("Failed to load experience details") }
        }
    }
}