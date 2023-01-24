package com.example.googleapi.screen.booksearch.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchDataModel(
    val items: List<Items>
)

@Serializable
data class Items(
    val volumeInfo: VolumeInfo
)
///////////////////////////////////////////////// VOLUME INFO
@Serializable
data class VolumeInfo(
    val imageLinks: ImageLinks
)

@Serializable
data class ImageLinks(
    val thumbnail: String
)
