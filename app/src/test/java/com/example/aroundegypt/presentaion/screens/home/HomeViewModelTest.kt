package com.example.aroundegypt.presentaion.screens.home

import com.example.aroundegypt.data.local.TestExperienceDao
import com.example.aroundegypt.data.remote.TestApi
import com.example.aroundegypt.data.repository.ExperienceRepositoryImpl
import com.example.aroundegypt.domain.model.Experience
import com.example.aroundegypt.utilitis.Resources
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Test
@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Test
    fun loadingStateForRecommended_isCorrect() = scope.runTest {
        val viewModel = getViewModel()
        val state = viewModel.recommendedExperiencesState.value
        assert(state == Resources.Loading<List<Experience>>())
    }

    private fun getViewModel(): HomeViewModel {
        return HomeViewModel(
            ExperienceRepositoryImpl(
                TestApi(),
                TestExperienceDao()
            ),
            dispatcher
        )
    }
}

