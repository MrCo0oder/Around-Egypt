package com.example.aroundegypt.data.remote.api

import com.example.aroundegypt.data.remote.model.experienceResponse.ExperienceDTO
import com.example.aroundegypt.data.remote.model.headerResponse.HeaderResponse
import com.example.aroundegypt.utilitis.Constants.API_VERSION
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("v{version}/experiences?filter[recommended]=true")
    suspend fun getRecommendedList(
        @Path("version") version: Int = API_VERSION,
    ): HeaderResponse<List<ExperienceDTO?>?>

    @GET("v{version}/experiences")
    suspend fun getMostRecentList(
        @Path("version") version: Int = API_VERSION,
    ): HeaderResponse<List<ExperienceDTO?>?>

    @GET("v{version}/experiences")
    suspend fun searchExperiences(
        @Path("version") version: Int = API_VERSION,
        @Query("filter[title]") query: String,
        ): HeaderResponse<List<ExperienceDTO?>?>

    @GET("v{version}/experiences/{id}")
    suspend fun getExperienceDetails(
        @Path("id") id: String,
        @Path("version") version: Int = API_VERSION,
    ): HeaderResponse<ExperienceDTO?>

    @POST("v{version}/experiences/{id}/like")
    suspend fun likeExperience(
        @Path("id") id: String,
        @Path("version") version: Int = API_VERSION,
    ): HeaderResponse<Int>

}