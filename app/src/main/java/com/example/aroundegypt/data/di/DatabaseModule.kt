package com.example.aroundegypt.data.di

import android.content.Context
import androidx.room.Room
import com.example.aroundegypt.data.local.dao.ExperienceDao
import com.example.aroundegypt.utilitis.Constants.AROUND_EGYPT_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule @Inject constructor() {

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            AROUND_EGYPT_DATABASE
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(db: AppDatabase): ExperienceDao {
        return db.experienceDao()
    }
}