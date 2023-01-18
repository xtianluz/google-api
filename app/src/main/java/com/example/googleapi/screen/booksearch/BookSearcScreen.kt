package com.example.googleapi.screen.booksearch

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.googleapi.R
import com.example.googleapi.screen.booksearch.DataSamples.SampleImageOne
import com.example.googleapi.ui.theme.GoogleApiTheme

@Composable
fun BookSearchScreen(searchViewModel: BookSearchViewModel = viewModel()){
    Column {
        SearchBar(
            userInput = searchViewModel.userInput,
            onUserInputChange = { searchViewModel.updateUserInput(it) }
        )
        SearchResult()
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
fun SearchResult(){
    BookThumbnail()
}

@Composable
fun BookThumbnail(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(360.dp)
            .padding(16.dp),
        elevation = 16.dp
    ){
        AsyncImage(
            model = SampleImageOne,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            error = painterResource(id = R.drawable.ic_connection_error),
            placeholder = painterResource(id = R.drawable.ic_broken_image),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
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