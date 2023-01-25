package com.example.googleapi.screen.data

import com.example.googleapi.screen.booksearch.SearchDataModel
import com.example.googleapi.screen.network.SearchApiService
import retrofit2.Response

interface SearchRepository {
    suspend fun getSearchItems(input: String): SearchDataModel
    suspend fun getCeciroItem(): Response<SearchDataModel>?
}

class SearchRepositoryClass(private val searchApiService: SearchApiService) : SearchRepository {
    override suspend fun getSearchItems(input: String): SearchDataModel {
        return searchApiService.getItems(input)
    }
    override suspend fun getCeciroItem(): Response<SearchDataModel>? {
        return searchApiService.getCeciro()
    }
}