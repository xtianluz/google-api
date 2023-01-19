package com.example.googleapi.screen.booksearch

import android.media.Image
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import kotlinx.coroutines.launch
import retrofit2.Response

class BookSearchViewModel : ViewModel(){

    var searchUiState: String by mutableStateOf(searchUiState)
        private set

    var userInput by mutableStateOf("")
        private set

    fun updateUserInput(userNewInput: String){
        userInput = userNewInput
    }

    init {
        getSearchItems()
    }

    private fun getSearchItems(){
        viewModelScope.launch {
           searchUiState = try{
                val result = SearchApi.retrofitService.getItems()
                result.items
            } catch (e: HttpException){
                "Error"
            }
        }
    }
}