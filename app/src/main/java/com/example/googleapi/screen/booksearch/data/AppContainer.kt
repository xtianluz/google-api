package com.example.googleapi.screen.booksearch.data

import com.example.googleapi.screen.booksearch.network.SearchApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainerInterface {
    val searchRepository: SearchRepositoryInterface
}

class AppContainer: AppContainerInterface {
    private val GOOGLE_URL = "https://www.googleapis.com/books/v1/"
    @OptIn(ExperimentalSerializationApi::class)
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        explicitNulls = false
        encodeDefaults = false
    }
    private val contentType = "application/json".toMediaType()
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory(contentType))
        .baseUrl(GOOGLE_URL)
        .build()

    private val retrofitService: SearchApiService by lazy {
        retrofit.create(SearchApiService::class.java)
    }
    override val searchRepository: SearchRepositoryInterface by lazy {
        SearchRepository(retrofitService)
    }

}