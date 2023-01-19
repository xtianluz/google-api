package com.example.googleapi.screen.booksearch

import android.media.Image
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST

private const val GOOGLE_URL = "https://www.googleapis.com/books/v1/volumes?q="

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(GOOGLE_URL)
    .build()


@Serializable
data class Thumbnail(
    val thumbnail: String
)

@Serializable
data class ImageLinks(
    val imageLinks: Thumbnail,
)

@Serializable
data class Items(
    val items: String
)

interface GoogleApiService {
    @GET("ceciro")
    suspend fun getItems() : Items
}

object SearchApi {
    val retrofitService: GoogleApiService by lazy {
        retrofit.create(GoogleApiService::class.java)
    }
}
