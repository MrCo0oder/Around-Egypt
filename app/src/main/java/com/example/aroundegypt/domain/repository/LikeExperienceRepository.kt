package com.example.aroundegypt.domain.repository

import com.example.aroundegypt.domain.model.Experience
import com.example.aroundegypt.utilitis.Resources
import kotlinx.coroutines.flow.Flow

interface LikeExperienceRepository {
    suspend operator fun invoke(id: String): Flow<Resources<Int>>
}