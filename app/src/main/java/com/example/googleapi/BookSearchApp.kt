package com.example.googleapi

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.googleapi.screen.booksearch.BookSearchScreen
import com.example.googleapi.screen.booksearch.BookSearchViewModel

@Composable
fun BookSearchApp(searchViewModel: BookSearchViewModel){

    BookSearchScreen(searchViewModel.searchUiState, searchViewModel)
}