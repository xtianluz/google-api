package com.example.googleapi.screen.booksearch.data

import com.example.googleapi.screen.booksearch.SearchApi.retrofitService
import com.example.googleapi.screen.booksearch.model.SearchDataModel
import com.example.googleapi.screen.booksearch.network.SearchApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface SearchRepositoryInterface{
    suspend fun getSearchItems(input: String): SearchDataModel
}

class SearchRepository(private val searchApiService: SearchApiService) : SearchRepositoryInterface{

    override suspend fun getSearchItems(input: String): SearchDataModel {
        return searchApiService.searchItems(input)
    }

}