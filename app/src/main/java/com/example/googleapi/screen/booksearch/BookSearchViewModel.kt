package com.example.googleapi.screen.booksearch


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import kotlinx.coroutines.launch

sealed interface BookSearchUiState {
    data class Success(val searchedItems: List<String>) : BookSearchUiState
    object Error: BookSearchUiState
    object  Loading: BookSearchUiState
}

class BookSearchViewModel : ViewModel(){

    var searchUiState: BookSearchUiState by mutableStateOf(BookSearchUiState.Loading)
        private set

    var userInput by mutableStateOf("")
        private set


    private var thumbnailList: MutableList<String> = mutableListOf()

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
                val items = result.items
                items?.forEach { i -> i.volumeInfo?.imageLinks?.thumbnail?.replace("http", "https")?.let { thumbnailList.add(it)} }
                BookSearchUiState.Success(thumbnailList)
            } catch (e: HttpException){
                BookSearchUiState.Error
            }
        }
    }
}


