package com.example.aroundegypt.data.repository

import com.example.aroundegypt.data.local.dao.ExperienceDao
import com.example.aroundegypt.data.remote.api.ApiService
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
                if (experienceDao.getExperienceById(id)?.isLiked == 1) {
                    emit(
                        Resources.Success(
                            data = experienceDao.getExperienceById(id)?.likesNo
                        )
                    )
                    return@flow
                }
                val response = apiService.likeExperience(id)
                if (response.meta?.code == 200) {
                    val updatedExperience = experienceDao.getExperienceById(id)
                    updatedExperience?.let {
                        experienceDao.updateLikeStatus(it.id, 1)
                        experienceDao.updateLikesNo(id, response.data!!)
                    }
                    emit(Resources.Success(data = response.data))
                } else {
                    emit(Resources.Error(response.meta?.errors?.joinToString("\n") ?: ""))
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