package com.example.aroundegypt.domain.repository

import com.example.aroundegypt.domain.model.Experience
import com.example.aroundegypt.utilitis.Resources
import kotlinx.coroutines.flow.Flow

interface ExperienceRepository {
    fun getRecommendedList(
    ): Flow<Resources<List<Experience>>>

    fun getMostRecentList(
    ): Flow<Resources<List<Experience>>>

    fun getFilteredList(query: String): Flow<Resources<List<Experience>>>
}