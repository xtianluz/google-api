package com.example.googleapi.screen.booksearch


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.network.HttpException
import com.example.googleapi.SearchApplication
import com.example.googleapi.screen.data.SearchRepository
import com.example.googleapi.screen.data.SearchRepositoryClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Dispatcher
import java.io.IOException

sealed interface BookSearchUiState {
    data class Success(val searchedItems: MutableList<String>) : BookSearchUiState
    object Error: BookSearchUiState
    object  Loading: BookSearchUiState
}

class BookSearchViewModel(private val searchRepository: SearchRepository?) : ViewModel(){

    var searchUiState: BookSearchUiState by mutableStateOf(BookSearchUiState.Loading)
        private set

    var userInput by mutableStateOf("ceciro")
        private set

    private var thumbnailList: MutableList<String> = mutableListOf()

    fun updateUserInput(newUserInput: String){
        userInput = newUserInput
    }

    fun getSearch(){
//        thumbnailList.clear()
//        getSearchItems()
        runBlocking{
            val result = searchRepository?.getSearchItems("ceciro")
            val items = result?.items
            println(items)
        }
    }

    private fun getSearchItems(){
        searchUiState = BookSearchUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val result = searchRepository?.getSearchItems(userInput)
                val items = result?.items
                launch{
//                    items?.forEach { i ->
//                        i.volumeInfo?.imageLinks?.thumbnail?.replace(
//                            "http",
//                            "https"
//                        )?.let { thumbnailList.add(it) }
//                    }
                    items?.forEach {i ->
                        i.volumeInfo?.imageLinks?.thumbnail?.let { thumbnailList.add(it) }
                    }
                    searchUiState = BookSearchUiState.Success(thumbnailList)
                }

                println("success success success success success success success")
                println(userInput)
            } catch (e: HttpException){
                searchUiState = BookSearchUiState.Error
                println("error error error error error error error")
            } catch(e: IOException){
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
