package com.example.aroundegypt.presentaion.screens.home

import com.example.aroundegypt.data.local.dao.ExperienceDao
import com.example.aroundegypt.data.local.entities.ExperienceEntity
import com.example.aroundegypt.data.remote.api.ApiService
import com.example.aroundegypt.data.remote.model.experienceResponse.ExperienceDTO
import com.example.aroundegypt.data.remote.model.headerResponse.HeaderResponse
import com.example.aroundegypt.data.repository.ExperienceRepositoryImpl
import com.example.aroundegypt.domain.model.Experience
import com.example.aroundegypt.domain.repository.ExperienceRepository
import com.example.aroundegypt.utilitis.Resources
import kotlinx.coroutines.flow.Flow
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Test
    fun loadingState_isCorrect() {
        val viewModel = getViewModel()
    }

    private fun getViewModel(): HomeViewModel {
        return HomeViewModel(
            ExperienceRepositoryImpl(
                TestApi(),
                experienceDao = TestExperienceDao()
            ),
            dispatcher
        )
    }
}

class TestExperienceDao : ExperienceDao {
    override suspend fun insertAll(experiences: List<ExperienceEntity>) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllExperiences(): List<ExperienceEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getFilteredExperiences(query: String): List<ExperienceEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getExperienceById(id: String): ExperienceEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun updateLikeStatus(id: String, isLiked: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun updateLikesNo(id: String, likesNo: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getRecommendedExperiences(): List<ExperienceEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun insertExperience(experience: ExperienceEntity) {
        TODO("Not yet implemented")
    }

}

class TestApi : ApiService {
    override suspend fun getRecommendedList(version: Int): HeaderResponse<List<ExperienceDTO?>?> {
        TODO("Not yet implemented")
    }

    override suspend fun getMostRecentList(version: Int): HeaderResponse<List<ExperienceDTO?>?> {
        TODO("Not yet implemented")
    }

    override suspend fun searchExperiences(
        version: Int,
        query: String
    ): HeaderResponse<List<ExperienceDTO?>?> {
        TODO("Not yet implemented")
    }

    override suspend fun getExperienceDetails(
        id: String,
        version: Int
    ): HeaderResponse<ExperienceDTO?> {
        return HeaderResponse(
            data = ExperienceDTO(
                address = address,
                audioUrl = audioUrl,
                city = city,
                coverPhoto = coverPhoto,
                description = description,
                detailedDescription = detailedDescription,
                era = era,
                experienceTips = experienceTips,
                famousFigure = famousFigure,
                founded = founded,
                gmapLocation = gmapLocation,
                hasAudio = hasAudio,
                hasVideo = hasVideo,
                id = id,
                isLiked = isLiked,
                likesNo = likesNo,
                openingHours = openingHours,
                period = period,
                rating = rating,
                recommended = recommended,
                reviews = reviews,
                reviewsNo = reviewsNo,
                startingPrice = startingPrice,
                tags = tags,
                ticketPrices = ticketPrices,
                title = title,
                tourHtml = tourHtml,
                translatedOpeningHours = translatedOpeningHours,
                viewsNo = viewsNo
            ), meta = null, pagination = null

        )
    }


    override suspend fun likeExperience(id: String, version: Int): HeaderResponse<Int> {
        return HeaderResponse(200, null, null)
    }

}

