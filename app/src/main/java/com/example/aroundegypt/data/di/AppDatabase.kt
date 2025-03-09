package com.example.aroundegypt.data.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.aroundegypt.data.local.dao.ExperienceDao
import com.example.aroundegypt.data.local.entities.ExperienceEntity

@Database(entities = [ExperienceEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun experienceDao(): ExperienceDao

}