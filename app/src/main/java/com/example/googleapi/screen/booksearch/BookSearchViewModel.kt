package com.example.googleapi.screen.booksearch


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.network.HttpException
import com.example.googleapi.SearchApplication
import com.example.googleapi.screen.booksearch.data.SearchRepositoryInterface
import com.example.googleapi.screen.booksearch.model.Items
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed interface BookSearchUiState {
    data class Success(val searchedItems: List<String>) : BookSearchUiState
    object Error: BookSearchUiState
    object  Loading: BookSearchUiState
}

class BookSearchViewModel(private val searchRepository: SearchRepositoryInterface?) : ViewModel(){

    var searchUiState: BookSearchUiState by mutableStateOf(BookSearchUiState.Loading)
        private set

    var userInput by mutableStateOf("")
        private set

    var thumbnailList: MutableList<String> = mutableListOf()
//        private set

    fun updateUserInput(newUserInput: String){
        userInput = newUserInput
    }

    fun getSearch(){
//        thumbnailList.clear()
        getSearchItems()
    }

    private fun getSearchItems(){
        searchUiState = BookSearchUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
             try{
                val result = searchRepository?.getSearchItems(userInput)
                val items = result?.items
//                items?.forEach { i -> i.volumeInfo?.imageLinks?.thumbnail?.replace("http", "https")}
                items?.forEach { i -> i.volumeInfo?.imageLinks?.thumbnail?.replace("http","https").let { thumbnailList } }

                 searchUiState = BookSearchUiState.Success(thumbnailList)

            } catch (e: HttpException){
                 searchUiState = BookSearchUiState.Error
            }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as? SearchApplication)
                val searchRepository = application?.container?.searchRepository
                BookSearchViewModel(searchRepository = searchRepository)
            }
        }
    }
}


