package com.example.aroundegypt.data.remote

import com.example.aroundegypt.DummyData
import com.example.aroundegypt.data.remote.api.ApiService
import com.example.aroundegypt.data.remote.model.experienceResponse.ExperienceDTO
import com.example.aroundegypt.data.remote.model.headerResponse.HeaderResponse

class TestApi : ApiService {
    override suspend fun getRecommendedList(version: Int): HeaderResponse<List<ExperienceDTO?>?> {
        return HeaderResponse(DummyData.getDummyExperiences(), null, null)
    }

    override suspend fun getMostRecentList(version: Int): HeaderResponse<List<ExperienceDTO?>?> {
        return HeaderResponse(DummyData.getDummyExperiences().subList(2, 4), null, null)
    }

    override suspend fun searchExperiences(
        version: Int,
        query: String
    ): HeaderResponse<List<ExperienceDTO?>?> {
        return HeaderResponse(DummyData.getDummyExperiences().shuffled(), null, null)

    }

    override suspend fun getExperienceDetails(
        id: String,
        version: Int
    ): HeaderResponse<ExperienceDTO?> {
        return HeaderResponse(
            data = DummyData.getDummyExperiences().first()
        )
    }


    override suspend fun likeExperience(id: String, version: Int): HeaderResponse<Int> {
        return HeaderResponse(DummyData.getDummyExperiences().first().likesNo, null, null)
    }

}