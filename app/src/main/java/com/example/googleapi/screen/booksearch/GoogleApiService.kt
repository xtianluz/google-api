package com.example.googleapi.screen.booksearch


import com.example.googleapi.screen.booksearch.model.SearchDataModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query


private const val GOOGLE_URL = "https://www.googleapis.com/books/v1/"

@OptIn(ExperimentalSerializationApi::class)
private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
    explicitNulls = false
    encodeDefaults = false
}

private val contentType = "application/json".toMediaType()

@OptIn(ExperimentalSerializationApi::class)
private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory(contentType))
    .baseUrl(GOOGLE_URL)
    .build()

interface GoogleApiService {
    @GET("volumes/")
    suspend fun getItems(@Query("q") input: String) : SearchDataModel

}

object SearchApi {
    val retrofitService: GoogleApiService by lazy {
        retrofit.create(GoogleApiService::class.java)
    }
}
