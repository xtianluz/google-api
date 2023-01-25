package com.example.googleapi.screen.data

import com.example.googleapi.screen.booksearch.SearchDataModel
import com.example.googleapi.screen.network.SearchApi

interface SearchRepository {
    suspend fun getSearchItems(input: String): SearchDataModel
}

class SearchRepositoryClass : SearchRepository {
    override suspend fun getSearchItems(input: String): SearchDataModel {
        return SearchApi.retrofitService.getItems(input)
    }
}