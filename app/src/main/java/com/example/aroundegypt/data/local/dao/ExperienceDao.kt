package com.example.aroundegypt.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aroundegypt.data.local.entities.ExperienceEntity

@Dao
interface ExperienceDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(experiences: List<ExperienceEntity>)

    @Query("SELECT * FROM experience_table")
    suspend fun getAllExperiences(): List<ExperienceEntity>

    @Query("SELECT * FROM experience_table WHERE title LIKE :query")
    suspend fun getFilteredExperiences(query: String): List<ExperienceEntity>

    @Query("SELECT * FROM experience_table WHERE id = :id")
    suspend fun getExperienceById(id: String): ExperienceEntity?

    @Query("UPDATE experience_table SET isLiked = :isLiked WHERE id = :id")
    suspend fun updateLikeStatus(id: String, isLiked: Int)


    @Query("UPDATE experience_table SET likesNo = :likesNo WHERE id = :id")
    suspend fun updateLikesNo(id: String, likesNo: Int)

    @Query("SELECT * FROM experience_table WHERE recommended = 1")
    suspend fun getRecommendedExperiences(): List<ExperienceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExperience(experience: ExperienceEntity)


}