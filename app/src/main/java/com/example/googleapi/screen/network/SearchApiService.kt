package com.example.googleapi.screen.network


import com.example.googleapi.screen.booksearch.SearchDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {
    @GET("volumes/")
    suspend fun getItems(@Query("q") input: String) : SearchDataModel

    @GET("volumes/?q=ceciro")
    suspend fun getCeciro() : Response<SearchDataModel>?
}


