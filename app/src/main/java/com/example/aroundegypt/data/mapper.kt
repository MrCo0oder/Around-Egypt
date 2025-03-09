package com.example.aroundegypt.data

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