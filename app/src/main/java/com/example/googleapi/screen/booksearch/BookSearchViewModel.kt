package com.example.googleapi.screen.booksearch


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

sealed interface BookSearchUiState {
    data class Success(val searchItems: Items) : BookSearchUiState
    object Error: BookSearchUiState
    object  Loading: BookSearchUiState
}

class BookSearchViewModel : ViewModel(){

    var searchUiState: BookSearchUiState by mutableStateOf(BookSearchUiState.Loading)
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
            searchUiState =
            try{
                val result = SearchApi.retrofitService.getItems()
                BookSearchUiState.Success(result)
            } catch (e: HttpException){
                BookSearchUiState.Error
            }
        }
    }
}