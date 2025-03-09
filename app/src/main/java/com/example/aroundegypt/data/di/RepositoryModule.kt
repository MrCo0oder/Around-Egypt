package com.example.aroundegypt.data.di

import com.example.aroundegypt.data.repository.ExperienceRepositoryImpl
import com.example.aroundegypt.data.repository.GetExperienceRepositoryImpl
import com.example.aroundegypt.data.repository.LikeExperienceRepositoryImpl
import com.example.aroundegypt.domain.repository.ExperienceRepository
import com.example.aroundegypt.domain.repository.GetExperienceRepository
import com.example.aroundegypt.domain.repository.LikeExperienceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindExperienceRepository(
        repository: ExperienceRepositoryImpl
    ): ExperienceRepository

    @Binds
    @Singleton
    abstract fun bindGetExperienceRepository(
        repository: GetExperienceRepositoryImpl
    ): GetExperienceRepository

    @Binds
    @Singleton
    abstract fun bindLikeExperienceRepository(
        repository: LikeExperienceRepositoryImpl
    ): LikeExperienceRepository

}