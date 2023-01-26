package com.example.googleapi.screen.booksearch

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchDataModel(
    @SerialName("items")
    val items: List<Items>?
)

@Serializable
data class Items(
    val volumeInfo: VolumeInfo?,
)
///////////////////////////////////////////////// VOLUME INFO
@Serializable
data class VolumeInfo(
    val imageLinks: ImageLinks?,
)

@Serializable
data class ImageLinks(
    val thumbnail: String?
)