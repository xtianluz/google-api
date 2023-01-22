package com.example.googleapi.screen.booksearch

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.googleapi.R
import com.example.googleapi.ui.theme.GoogleApiTheme

@Composable
fun BookSearchScreen(){
    val searchViewModel: BookSearchViewModel = viewModel()
    val searchUiState = searchViewModel.searchUiState
    Column {
        SearchBar(userInput = searchViewModel.userInput, onUserInputChange = { searchViewModel.updateUserInput(it) } )
        when(searchUiState){
            is BookSearchUiState.Success -> BookThumbnail(bookThumbnail = searchUiState.searchItems)
            is BookSearchUiState.Loading ->  TextResult(resultText = "Loading")
            is BookSearchUiState.Error -> TextResult(resultText = "Error")
        }
    }
}

@Composable
fun SearchBar(
    userInput: String,
    onUserInputChange: (String) -> Unit,
){
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = userInput,
            onValueChange = onUserInputChange,
            singleLine = true,
            shape = MaterialTheme.shapes.large
        )
    }
}

@Composable
fun BookshelfGrid(thumbnails: List<String>){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(6.dp),
        modifier = Modifier.fillMaxWidth()
    ){
        items(items = thumbnails){ item ->
            BookThumbnail(bookThumbnail = item)
        }
    }
}

@Composable
fun BookThumbnail(bookThumbnail: String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(16.dp),
        elevation = 16.dp
    ){
        AsyncImage(
            model = bookThumbnail,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            error = painterResource(id = R.drawable.ic_connection_error),
            placeholder = painterResource(id = R.drawable.ic_broken_image),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading)
        )
    }
}

@Composable
fun TextResult(resultText: String){
    Text(text = resultText)
}


/////////////////////////////////////////////////////////////////
////////        PREVIEW COMPOSABLE           ////////////////////
/////////////////////////////////////////////////////////////////

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    GoogleApiTheme() {
        SearchBar(userInput = "Placeolder", onUserInputChange = { } )
    }
}