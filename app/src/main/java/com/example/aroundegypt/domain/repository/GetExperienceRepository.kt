package com.example.aroundegypt.domain.repository

import com.example.aroundegypt.domain.model.Experience
import com.example.aroundegypt.utilitis.Resources
import kotlinx.coroutines.flow.Flow

interface GetExperienceRepository {
    suspend operator fun invoke(id: String): Flow<Resources<Experience>>
}