package com.example.googleapi.screen.booksearch.data


import com.example.googleapi.screen.booksearch.model.SearchDataModel
import com.example.googleapi.screen.booksearch.network.SearchApiService


interface SearchRepositoryInterface{
    suspend fun getSearchItems(input: String): SearchDataModel
}

class SearchRepository(private val searchApiService: SearchApiService) : SearchRepositoryInterface{
    override suspend fun getSearchItems(input: String): SearchDataModel {
        return searchApiService.searchItems(input)
    }
}