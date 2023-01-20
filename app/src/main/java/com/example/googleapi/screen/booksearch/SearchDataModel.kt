package com.example.googleapi.screen.booksearch

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchDataModel(
    val kind: String,
    val totalItems: Int,
    val items: List<Items>? = null
)

@Serializable
data class Items(
//    val kind: String? = null,
    val id: String? = null,
//    val etag: String? = null,
//    val selfLink: String? = null,
    val volumeInfo: VolumeInfo? = null,
//    val saleInfo: SaleInfo? = null,
//    val accessInfo: AccessInfo? = null,
//    val searchInfo: SearchInfo? = null
)

///////////////////////////////////////////////// VOLUME INFO

@Serializable
data class VolumeInfo(
//    val title: String? = null,
//    val subtitle: String? = null,
//    val authors: List<String>? = null,
//    val publisher: String? = null,
//    val publishedDate: String? = null,
//    val description: String? = null,
//    val industryIdentifiers: List<IndustryIdentifiers>? = null,
//    val readingModes: ReadingModes? = null,
//    val pageCount: Int? = null,
//    val printType: String? = null,
//    val categories: List<String>? = null,
//    val maturityRating: String? = null,
//    val allowAnonLogging: Boolean? = null,
//    val contentVersion: String? = null,
//    val panelizationSummary: PanelizationSummary? = null,
    val imageLinks: ImageLinks? = null,
//    val language: String? = null,
//    val previewLink: String? = null,
//    val infoLink: String? = null,
//    val canonicalVolumeLink: String? = null
)

@Serializable
data class IndustryIdentifiers(
    val type: String? = null,
    val identifier: String? = null
)

@Serializable
data class ReadingModes(
    val text: Boolean? = null,
    val image: Boolean? = null
)

@Serializable
data class PanelizationSummary(
    val containsEpubBubbles: Boolean? = null,
    val containsImageBubbles: Boolean? = null
)

@Serializable
data class ImageLinks(
    val smallThumbnail: String? = null,
    val thumbnail: String? = null
)

///////////////////////////////////////////////// SALE INFO

@Serializable
data class SaleInfo(
    val country: String? = null,
    val saleability: String? = null,
    val isEbook: Boolean? = null
)

///////////////////////////////////////////////// ACCESS INFO
@Serializable
data class AccessInfo(
    val country: String? = null,
    val viewability: String? = null,
    val embeddable: Boolean? = null,
    val publicDomain: Boolean? = null,
    val textToSpeechPermission: String? = null,
    val epub: Epub? = null,
    val pdf: Pdf? = null,
    val webReaderLink: String? = null,
    val accessViewStatus: String? = null,
    val quoteSharingAllowed: Boolean? = null
)

@Serializable
data class Epub(
    val isAvailable: Boolean? = null
)

@Serializable
data class Pdf(
    val isAvailable: Boolean? = null,
    val acsTokenLink: String? = null
)

///////////////////////////////////////////////// SEARCH INFO

@Serializable
data class SearchInfo(
    val textSnippet: String? = null
)