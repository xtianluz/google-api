

import com.example.googleapi.screen.booksearch.SearchApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException

fun main(){
    runBlocking {
        launch{
            try{
//                getImage()
                searchItem()
//                getThumbnail()
            }catch(e: HttpException){
                throw Exception("Error")
            }
        }
    }
}

suspend fun getImage(){
    val result = SearchApi.retrofitService.getItems()
    val imageLink = result.items?.first()?.volumeInfo?.imageLinks?.thumbnail
    val updatedLink = imageLink?.replace("http", "https")
    println(updatedLink)
}

suspend fun searchItem(){
    val searchResult = SearchApi.retrofitService.doSearch("shakespear")
    val textLink = searchResult.raw()
    println(textLink)
}

suspend fun getThumbnail(){
    val searchResult = SearchApi.retrofitService.getThumbnail("q=niel")
    val textLink = searchResult.raw()
    println(textLink)
}