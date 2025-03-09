package com.example.aroundegypt

import com.example.aroundegypt.data.remote.model.experienceResponse.City
import com.example.aroundegypt.data.remote.model.experienceResponse.Era
import com.example.aroundegypt.data.remote.model.experienceResponse.ExperienceDTO
import com.example.aroundegypt.data.remote.model.experienceResponse.GmapLocation
import com.example.aroundegypt.data.remote.model.experienceResponse.Period
import com.example.aroundegypt.data.remote.model.experienceResponse.Tag
import com.example.aroundegypt.data.remote.model.experienceResponse.TicketPrice
import com.example.aroundegypt.data.remote.model.experienceResponse.TranslatedOpeningHours

object DummyData {
    fun getDummyExperiences() = listOf(
        ExperienceDTO(
            address = "123 Main St",
            audioUrl = "https://example.com/audio.mp3",
            city = City(name = "New York"),
            coverPhoto = "https://example.com/photo.jpg",
            description = "A historical landmark in the city.",
            detailedDescription = "This landmark has been around for centuries...",
            era = Era(value = "Modern Era"),
            experienceTips = listOf("Bring a camera", "Visit early in the morning"),
            famousFigure = "John Doe",
            founded = "1892",
            gmapLocation = GmapLocation(coordinates = listOf(), type = null),
            hasAudio = true,
            hasVideo = 1,
            id = "exp_001",
            isLiked = false,
            likesNo = 120,
            openingHours = null,
            period = null,
            rating = 5,
            recommended = 95,
            reviews = null,
            reviewsNo = 50,
            startingPrice = 25,
            tags = listOf(Tag(name = "Historical"), Tag(name = "Must-See")),
            ticketPrices = listOf(TicketPrice(type = "Adult", price = 20)),
            title = "The Grand Landmark",
            tourHtml = "<p>Explore the beauty of history...</p>",
            translatedOpeningHours = TranslatedOpeningHours(),
            viewsNo = 5000
        ),
        ExperienceDTO(
            address = "456 Elm St",
            audioUrl = null,
            city = City(name = "Los Angeles"),
            coverPhoto = "https://example.com/photo2.jpg",
            description = "A famous museum with ancient artifacts.",
            detailedDescription = "Home to artifacts from various civilizations...",
            era = Era(value = "Ancient Era"),
            experienceTips = listOf("Wear comfortable shoes", "Take a guided tour"),
            famousFigure = "Jane Smith",
            founded = "1920",
            gmapLocation = null,
            hasAudio = false,
            hasVideo = 0,
            id = "exp_002",
            isLiked = true,
            likesNo = 300,
            openingHours = null,
            period = null,
            rating = 4,
            recommended = 85,
            reviews = null,
            reviewsNo = 75,
            startingPrice = 15,
            tags = listOf(Tag(name = "Museum"), Tag(name = "Educational")),
            ticketPrices = null,
            title = "The History Museum",
            tourHtml = "<p>Discover the ancient world...</p>",
            translatedOpeningHours = null,
            viewsNo = 7000
        ),
        ExperienceDTO(
            address = "789 Oak St",
            audioUrl = "https://example.com/audio3.mp3",
            city = City(name = "Chicago"),
            coverPhoto = "https://example.com/photo3.jpg",
            description = "A beautiful park with stunning landscapes.",
            detailedDescription = "This park offers scenic views and peaceful nature walks...",
            era = Era(value = "Contemporary"),
            experienceTips = listOf("Pack a picnic", "Enjoy the sunset"),
            famousFigure = "Emily Johnson",
            founded = "2005",
            gmapLocation = null,
            hasAudio = true,
            hasVideo = 1,
            id = "exp_003",
            isLiked = true,
            likesNo = 450,
            openingHours = null,
            period = Period(value = "21st Century"),
            rating = 5,
            recommended = 98,
            reviewsNo = 120,
            startingPrice = 0,
            tags = listOf(Tag(name = "Nature"), Tag(name = "Relaxing")),
            ticketPrices = listOf(),
            title = "Green Valley Park",
            tourHtml = "<p>Experience nature like never before...</p>",
            viewsNo = 12000
        ),
        ExperienceDTO(
            address = "321 Pine St",
            audioUrl = null,
            city = City(name = "San Francisco"),
            coverPhoto = "https://example.com/photo4.jpg",
            description = "A historic bridge with amazing architecture.",
            detailedDescription = "This bridge is an iconic landmark...",
            experienceTips = listOf("Take a guided tour", "Visit at night for the lights"),
            famousFigure = "Robert Williams",
            founded = "1937",
            hasAudio = false,
            hasVideo = 1,
            id = "exp_004",
            isLiked = false,
            likesNo = 250,
            rating = 4,
            recommended = 90,
            reviewsNo = 200,
            startingPrice = 0,
            tags = listOf(Tag(name = "Architecture"), Tag(name = "Landmark")),
            ticketPrices = listOf(),
            title = "Golden Gate Bridge",
            tourHtml = "<p>Walk across one of the most famous bridges...</p>",
            viewsNo = 15000
        )
    )
}