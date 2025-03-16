package com.example.aroundegypt.presentaion.screens.home

import com.example.aroundegypt.DummyData
import com.example.aroundegypt.data.local.TestExperienceDao
import com.example.aroundegypt.data.remote.TestApi
import com.example.aroundegypt.data.repository.ExperienceRepositoryImpl
import com.example.aroundegypt.data.toExperience
import com.example.aroundegypt.domain.model.Experience
import com.example.aroundegypt.domain.repository.ExperienceRepository
import com.example.aroundegypt.domain.repository.LikeExperienceRepository
import com.example.aroundegypt.utilitis.Resources
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private val dispatcher = UnconfinedTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Test
    fun loadingStateForRecommended_isCorrect() = scope.runTest {
        val viewModel = getViewModel()
        val state = viewModel.recommendedExperiencesState.value
        assert(state is Resources.Loading)
    }

    @Test
    fun loadingStateForMostRecent_isCorrect() = scope.runTest {
        val viewModel = getViewModel()
        val state = viewModel.mostRecentExperiencesState.value
        assert(state is Resources.Loading)
    }

    @Test
    fun loadingStateForFiltered_isCorrect() = scope.runTest {
        val viewModel = getViewModel()
        val state = viewModel.filteredExperiencesState.value
        assert(state is Resources.Loading)
    }

    @Test
    fun successStateForRecommended_isCorrect() = scope.runTest {

        val viewModel = getViewModel(
            rep = fakeRepository
        )
        backgroundScope.launch {
            viewModel.getRecommendedList()
        }
        runCurrent()
        val state = viewModel.recommendedExperiencesState.value
        println(
            "state: ${state.data} , ${
                DummyData.getDummyExperiences().map { it.toExperience() }
            }"
        )
        assertEquals(
            DummyData.getDummyExperiences().map { it.toExperience() }, state.data
        )
    }

    @Test
    fun successStateForMostRecent_isCorrect() = scope.runTest {
        val viewModel = getViewModel(
            rep = fakeRepository
        )
        backgroundScope.launch {
            viewModel.getMostRecentList()
        }
        runCurrent()
        val state = viewModel.mostRecentExperiencesState.value
        println(
            "state: ${state.data} , ${
                DummyData.getDummyExperiences().map { it.toExperience() }
            }"
        )
        assertEquals(
            DummyData.getDummyExperiences().map { it.toExperience() }, state.data
        )
    }

    @Test
    fun successStateForFiltered_isCorrect() = scope.runTest {
        val viewModel = getViewModel(
            rep = fakeRepository
        )
        backgroundScope.launch {
            viewModel.getFilteredList("")
        }
        runCurrent()
        val state = viewModel.filteredExperiencesState.value
        println(
            "state: ${state.data} , ${
                DummyData.getDummyExperiences().map { it.toExperience() }
            }"
        )
        assertEquals(
            DummyData.getDummyExperiences().map { it.toExperience() }, state.data
        )
    }

    @Test
    fun errorStateForRecommended_isCorrect() = scope.runTest {
        val viewModel = getViewModel(
            rep = fakeErrorRepository
        )
        backgroundScope.launch {
            viewModel.getRecommendedList()
        }
        runCurrent()
        val state = viewModel.recommendedExperiencesState.value
        println("state: ${state.data} , error1")
        assertEquals("error1", state.message)
    }

    @Test
    fun errorStateForMostRecent_isCorrect() = scope.runTest {
        val viewModel = getViewModel(
            rep = fakeErrorRepository
        )
        backgroundScope.launch {
            viewModel.getMostRecentList()
        }
        runCurrent()
        val state = viewModel.mostRecentExperiencesState.value
        println("state: ${state.data} , error2")
        assertEquals("error2", state.message)
    }

    @Test
    fun errorStateForFiltered_isCorrect() = scope.runTest {
        val viewModel = getViewModel(
            rep = fakeErrorRepository
        )
        backgroundScope.launch {
            viewModel.getFilteredList("")
        }
        runCurrent()
        val state = viewModel.filteredExperiencesState.value
        println("state: ${state.data} , error3")
        assertEquals("error3", state.message)
    }

    private fun getViewModel(
        rep: ExperienceRepository = ExperienceRepositoryImpl(
            TestApi(),
            TestExperienceDao(),
            context = null
        )
    ): HomeViewModel {
        return HomeViewModel(
            rep,
            likeRepository = fakeLikeRepository,
            dispatcher

        )
    }
}
val fakeLikeRepository = object : LikeExperienceRepository {
    override suspend fun invoke(id: String): Flow<Resources<Int>> =
        flowOf(Resources.Success(1))
}
val fakeRepository = object : ExperienceRepository {
    override fun getRecommendedList(): Flow<Resources<List<Experience>>> =
        flowOf(Resources.Success(DummyData.getDummyExperiences().map { it.toExperience() }))

    override fun getMostRecentList(): Flow<Resources<List<Experience>>> =
        flowOf(Resources.Success(DummyData.getDummyExperiences().map { it.toExperience() }))

    override fun getFilteredList(filterQuery: String): Flow<Resources<List<Experience>>> =
        flowOf(Resources.Success(DummyData.getDummyExperiences().map { it.toExperience() }))
}
val fakeErrorRepository = object : ExperienceRepository {
    override fun getRecommendedList(): Flow<Resources<List<Experience>>> =
        flowOf(Resources.Error("error1"))

    override fun getMostRecentList(): Flow<Resources<List<Experience>>> =
        flowOf(Resources.Error("error2"))

    override fun getFilteredList(filterQuery: String): Flow<Resources<List<Experience>>> =
        flowOf(Resources.Error("error3"))
}