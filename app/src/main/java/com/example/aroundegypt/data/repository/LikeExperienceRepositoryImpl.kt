package com.example.aroundegypt.data.repository

import com.example.aroundegypt.data.local.dao.ExperienceDao
import com.example.aroundegypt.data.local.entities.ExperienceEntity
import com.example.aroundegypt.data.remote.api.ApiService
import com.example.aroundegypt.data.toExperience
import com.example.aroundegypt.domain.model.Experience
import com.example.aroundegypt.domain.repository.GetExperienceRepository
import com.example.aroundegypt.domain.repository.LikeExperienceRepository
import com.example.aroundegypt.utilitis.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LikeExperienceRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val experienceDao: ExperienceDao
) : LikeExperienceRepository {

    override suspend fun invoke(id: String): Flow<Resources<Int>> {

            return flow {
                emit(Resources.Loading())

                try {
                    // Check Room for the like status
                    val cachedLikeStatus = experienceDao.getExperienceById(id)

                        // If not found in the cache, call the API to like the experience
                        val apiResponse = apiService.likeExperience(id)

                        if (apiResponse.meta?.code == 200) {

                            experienceDao.updateLikeStatus(id, 1)

                            experienceDao.updateLikesNo(id,  apiResponse.data!!)

                            emit(Resources.Success(data = apiResponse.data))
                        } else {
                            emit(Resources.Error(apiResponse.meta?.errors?.joinToString("\n") ?: ""))

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