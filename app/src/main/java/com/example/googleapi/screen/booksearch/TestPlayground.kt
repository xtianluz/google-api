

import com.example.googleapi.screen.booksearch.SearchApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue




fun main(){


    runBlocking {
        launch{
            try{
//                getImage()
//                searchItem()
//                getThumbnail()
                getList()
            }catch(e: HttpException){
                throw Exception("Error")
            }
        }
    }
}
suspend fun getList(){
    
    var thumbnailList = mutableListOf<String>()

    
    val searchResult = SearchApi.retrofitService.getThumbnail("niel")
    println(searchResult)

    val itemsInfo = searchResult.body()?.items
    println(itemsInfo)

    val valueInfo = itemsInfo?.get(0)?.volumeInfo?.imageLinks?.thumbnail
    println(valueInfo)

    itemsInfo?.forEach { i -> println(i.volumeInfo?.imageLinks?.thumbnail) }

    itemsInfo?.forEach { i -> i.volumeInfo?.imageLinks?.thumbnail?.let { thumbnailList.add(it) } }
    
    println(thumbnailList)
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
    val textLink = searchResult.toString()
    println(textLink)
}