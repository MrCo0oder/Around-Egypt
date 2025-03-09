package com.example.aroundegypt.data.repository

import com.example.aroundegypt.data.local.dao.ExperienceDao
import com.example.aroundegypt.data.remote.api.ApiService
import com.example.aroundegypt.data.toEntity
import com.example.aroundegypt.data.toExperience
import com.example.aroundegypt.domain.model.Experience
import com.example.aroundegypt.domain.repository.ExperienceRepository
import com.example.aroundegypt.utilitis.Resources
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExperienceRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val experienceDao: ExperienceDao
) : ExperienceRepository {

    override fun getRecommendedList(): Flow<Resources<List<Experience>>> {
        return flow {
            emit(Resources.Loading())

            try {
                val cachedExperiences = experienceDao.getRecommendedExperiences()

                if (cachedExperiences.isNotEmpty()) {
                    emit(Resources.Success(data = cachedExperiences.map { it.toExperience() }))
                } else {
                    val recommendedList = apiService.getRecommendedList()
                    if (recommendedList.meta?.code == 200) {
                        val experiences = recommendedList.data?.map {
                            it?.toExperience() ?: Experience()
                        } ?: emptyList()
                        experienceDao.insertAll(experiences.map { it.toEntity() })
                        emit(Resources.Success(data = experiences))
                    } else {
                        emit(Resources.Error(recommendedList.meta?.errors?.joinToString("\n") ?: ""))
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

    override fun getMostRecentList(): Flow<Resources<List<Experience>>> {
        return flow {
            emit(Resources.Loading())

            try {
                val cachedExperiences = experienceDao.getAllExperiences()

                if (cachedExperiences.isNotEmpty()) {
                    emit(Resources.Success(data = cachedExperiences.map { it.toExperience() }))
                } else {
                    val mostRecentList = apiService.getMostRecentList()
                    if (mostRecentList.meta?.code == 200) {
                        val experiences = mostRecentList.data?.map {
                            it?.toExperience() ?: Experience()
                        } ?: emptyList()
                        experienceDao.insertAll(experiences.map { it.toEntity() })
                        emit(Resources.Success(data = experiences))
                    } else {
                        emit(Resources.Error(mostRecentList.meta?.errors?.joinToString("\n") ?: ""))
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

    override fun getFilteredList(query: String): Flow<Resources<List<Experience>>> {
        return flow {
            emit(Resources.Loading())

            try {
                val cachedExperiences = experienceDao.getFilteredExperiences(query)

                if (cachedExperiences.isNotEmpty()) {
                    emit(Resources.Success(data = cachedExperiences.map { it.toExperience() }))
                } else {
                    delay(500)
                    val call = apiService.searchExperiences(query=query)

                    if (call.meta?.code == 200) {
                        val experiences = call.data?.map {
                            it?.toExperience() ?: Experience()
                        } ?: emptyList()
                        experienceDao.insertAll(experiences.map { it.toEntity() })
                        emit(Resources.Success(data = experiences))
                    } else {
                        emit(Resources.Error(call.meta?.errors?.joinToString("\n") ?: ""))
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