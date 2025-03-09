package com.example.aroundegypt.data.repository

import com.example.aroundegypt.data.remote.api.ApiService
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
    private val apiService: ApiService
) : ExperienceRepository {
    override fun getRecommendedList(): Flow<Resources<List<Experience>>> {
        return flow {

            emit(Resources.Loading())

            try {
                val recommendedList = apiService.getRecommendedList()
                if (recommendedList.meta?.code == 200)
                    emit(Resources.Success(data = recommendedList.data?.map {
                        it?.toExperience() ?: Experience()
                    }))
                else
                    emit(
                        Resources.Error(
                            recommendedList.meta?.errors?.joinToString("\n") ?: ""
                        )
                    )
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
                val mostRecentList = apiService.getMostRecentList()
                if (mostRecentList.meta?.code == 200)
                    emit(Resources.Success(data = mostRecentList.data?.map {
                        it?.toExperience() ?: Experience()
                    }))
                else
                    emit(
                        Resources.Error(
                            mostRecentList.meta?.errors?.joinToString("\n") ?: ""
                        )
                    )
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
                delay(500)
                val call = apiService.searchExperiences(
                    query = query,
                )
                if (call.meta?.code == 200)
                    emit(Resources.Success(data = call.data?.map {
                        it?.toExperience() ?: Experience()
                    }))
                else
                    emit(
                        Resources.Error(
                            call.meta?.errors?.joinToString("\n")
                                ?: ""
                        )
                    )
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