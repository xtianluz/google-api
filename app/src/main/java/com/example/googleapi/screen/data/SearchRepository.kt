package com.example.googleapi.screen.data

import com.example.googleapi.screen.booksearch.SearchDataModel
import com.example.googleapi.screen.network.SearchApiService

interface SearchRepository {
    suspend fun getSearchItems(input: String): SearchDataModel
}

class SearchRepositoryClass(private val searchApiService: SearchApiService) : SearchRepository {
    override suspend fun getSearchItems(input: String): SearchDataModel {
        return searchApiService.getItems(input)
    }
}