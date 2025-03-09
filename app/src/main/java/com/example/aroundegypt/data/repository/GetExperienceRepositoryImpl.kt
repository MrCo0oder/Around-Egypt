package com.example.aroundegypt.data.repository

import com.example.aroundegypt.data.local.dao.ExperienceDao
import com.example.aroundegypt.data.remote.api.ApiService
import com.example.aroundegypt.data.toEntity
import com.example.aroundegypt.data.toExperience
import com.example.aroundegypt.domain.model.Experience
import com.example.aroundegypt.domain.repository.GetExperienceRepository
import com.example.aroundegypt.utilitis.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetExperienceRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val experienceDao: ExperienceDao
) : GetExperienceRepository {

    override suspend fun invoke(id: String): Flow<Resources<Experience>> {
        return flow {
            emit(Resources.Loading())

            try {
                val cachedExperience = experienceDao.getExperienceById(id)

                if (cachedExperience != null) {
                    emit(Resources.Success(data = cachedExperience.toExperience()))
                } else {
                    val apiResponse = apiService.getExperienceDetails(id)

                    if (apiResponse.meta?.code == 200) {
                        val experience = apiResponse.data?.toExperience()

                        if (experience != null) {
                            experienceDao.insertExperience(experience.toEntity())
                        }

                        emit(Resources.Success(data = experience))
                    } else {
                        emit(Resources.Error(apiResponse.meta?.errors?.joinToString("\n") ?: ""))
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resources.Error("Could not load data"))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resources.Error("Could not load data"))
            }
        }
    }
}