package com.example.googleapi.screen.booksearch

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.googleapi.R
import com.example.googleapi.ui.theme.GoogleApiTheme

@Composable
fun BookSearchScreen(searchViewModel: BookSearchViewModel){

    Column {
        SearchBar(
            userInput = searchViewModel.userInput,
            onUserInputChange = { searchViewModel.updateUserInput(it) },
            onSearch = { searchViewModel.getSearch() },
        )
        when(searchViewModel.searchUiState){
            is BookSearchUiState.Success -> ThumbnailsGrid(thumbnails = (searchViewModel.searchUiState as BookSearchUiState.Success).searchedItems)
            is BookSearchUiState.Loading ->  LoadingScreen()
            is BookSearchUiState.Error -> ErrorScreen()
        }
    }
}

@Composable
fun SearchBar(
    userInput: String,
    onUserInputChange: (String) -> Unit,
    onSearch: () -> Unit,
){
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = userInput,
            onValueChange = onUserInputChange,
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            textStyle = MaterialTheme.typography.body2,
            trailingIcon = {
                IconButton(onClick = onSearch) {
                    Icon(Icons.Filled.Search, contentDescription = "Search")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {

                }
            )
        )
    }
}

@Composable
fun ThumbnailsGrid(thumbnails: List<String>){
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
            placeholder = painterResource(id = R.drawable.loading_img),
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
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading),
            modifier = Modifier.size(300.dp)
        )
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(stringResource(R.string.something_went_wrong))
    }
}


/////////////////////////////////////////////////////////////////
////////        PREVIEW COMPOSABLE           ////////////////////
/////////////////////////////////////////////////////////////////

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    GoogleApiTheme() {

    }
}