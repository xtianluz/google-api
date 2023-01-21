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

    val id: String?,

    @SerialName("volumeInfo")
    val volumeInfo: VolumeInfo?,

)
///////////////////////////////////////////////// VOLUME INFO
@Serializable
data class VolumeInfo(
    val title: String?,
    val authors: List<String>?,
    val description: String?,

    @SerialName("imageLinks")
    val imageLinks: ImageLinks?,
)

@Serializable
data class ImageLinks(
    @SerialName("smallThumbnail")
    val smallThumbnail: String?,
    @SerialName("thumbnail")
    val thumbnail: String?
)
