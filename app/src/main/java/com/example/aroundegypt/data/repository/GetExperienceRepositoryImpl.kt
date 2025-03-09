package com.example.aroundegypt.data.repository

import com.example.aroundegypt.data.remote.api.ApiService
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
    private val apiService: ApiService
) : GetExperienceRepository {
    override suspend fun invoke(id: String): Flow<Resources<Experience>> {
        return flow {

            emit(Resources.Loading())

            try {
                if (apiService.getExperienceDetails(id).meta?.code == 200)
                    emit(Resources.Success(data = apiService.getExperienceDetails(id).data?.toExperience()))
                else
                    emit(
                        Resources.Error(
                            apiService.getExperienceDetails(id).meta?.errors?.joinToString("\n")
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