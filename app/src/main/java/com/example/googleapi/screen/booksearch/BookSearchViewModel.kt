package com.example.googleapi.screen.booksearch

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class BookSearchViewModel : ViewModel(){
    var userInput by mutableStateOf("")
        private set

    fun updateUserInput(userNewInput: String){
        userInput = userNewInput
    }
}