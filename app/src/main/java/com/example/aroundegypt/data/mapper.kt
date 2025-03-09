package com.example.aroundegypt.data

import com.example.aroundegypt.data.local.entities.ExperienceEntity
import com.example.aroundegypt.data.remote.model.experienceResponse.ExperienceDTO
import com.example.aroundegypt.domain.model.Experience

fun ExperienceDTO.toExperience(): Experience {
    return Experience(
        address = address ?: "",
        city = city?.name ?: "",
        coverPhoto = coverPhoto ?: "",
        description = description ?: "",
        id = id ?: "0",
        isLiked = isLiked ?: false,
        likesNo = likesNo ?: 0,
        recommended = recommended == 1,
        title = title ?: "",
        tourHtml = tourHtml ?: "",
        viewsNo = viewsNo ?: 0
    )
}

fun Experience.toEntity(): ExperienceEntity {
    return ExperienceEntity(
        address = address,
        city = city ,
        coverPhoto = coverPhoto ,
        description = description ,
        id = id ,
        isLiked = if (isLiked) 1 else 0,
        likesNo = likesNo,
        recommended = if (recommended) 1 else 0,
        title = title,
        tourHtml = tourHtml,
        viewsNo = viewsNo
    )
}

fun ExperienceEntity.toExperience(): Experience {
    return Experience(
        address = address,
        city = city ,
        coverPhoto = coverPhoto ,
        description = description ,
        id = id ,
        isLiked = isLiked == 1,
        likesNo = likesNo,
        recommended = recommended == 1,
        title = title,
        tourHtml = tourHtml,
        viewsNo = viewsNo
    )
}