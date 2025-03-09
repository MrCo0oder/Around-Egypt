package com.example.aroundegypt.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "experience_table")
class ExperienceEntity(
    @PrimaryKey val id: String = "",
    val address: String = "",

    val city: String = "",

    val coverPhoto: String = "",

    val description: String = "",

    val detailedDescription: String = "",

    var isLiked: Int = 0,

    var likesNo: Int = 0,

    val recommended: Int = 0,

    val title: String = "",

    val tourHtml: String = "",

    val viewsNo: Int = 0
)
