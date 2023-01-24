package com.example.googleapi.screen.booksearch.network

import com.example.googleapi.screen.booksearch.model.SearchDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {
    @GET("volumes/")
    suspend fun searchItems(@Query("q") input: String) : SearchDataModel
}