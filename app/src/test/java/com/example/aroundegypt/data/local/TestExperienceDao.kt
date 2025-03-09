package com.example.aroundegypt.data.local

import com.example.aroundegypt.data.local.dao.ExperienceDao
import com.example.aroundegypt.data.local.entities.ExperienceEntity

class TestExperienceDao : ExperienceDao {
    private val experiencesFakeDB = HashMap<String, ExperienceEntity>()
    override suspend fun insertAll(experiences: List<ExperienceEntity>) {
        experiencesFakeDB.putAll(experiences.associateBy { it.id })
    }

    override suspend fun getAllExperiences(): List<ExperienceEntity> {
        return experiencesFakeDB.values.toList()
    }

    override suspend fun getFilteredExperiences(query: String): List<ExperienceEntity> {
        return experiencesFakeDB.values.toList().filter { it.title.contains(query) }
    }

    override suspend fun getExperienceById(id: String): ExperienceEntity? {
        return experiencesFakeDB[id]
    }

    override suspend fun updateLikeStatus(id: String, isLiked: Int) {
        experiencesFakeDB[id]?.isLiked = isLiked
    }

    override suspend fun updateLikesNo(id: String, likesNo: Int) {
        experiencesFakeDB[id]?.likesNo = likesNo
    }

    override suspend fun getRecommendedExperiences(): List<ExperienceEntity> {
        return experiencesFakeDB.values.toList()
    }

    override suspend fun insertExperience(experience: ExperienceEntity) {
        experiencesFakeDB[experience.id] = experience
    }

}