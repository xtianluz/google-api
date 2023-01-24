

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
                getList()
            }catch(e: HttpException){
                throw Exception("Error")
            }
        }
    }
}
suspend fun getList(){
    
    var thumbnailList = mutableListOf<String>()

    val searchResult = SearchApi.retrofitService.getItems("niel")
    println(searchResult)

    val itemsInfo = searchResult?.items
    println(itemsInfo)

    val valueInfo = itemsInfo?.get(0)?.volumeInfo?.imageLinks?.thumbnail
    println(valueInfo)

    itemsInfo?.forEach { i -> println(i.volumeInfo?.imageLinks?.thumbnail) }

    itemsInfo?.forEach { i -> i.volumeInfo?.imageLinks?.thumbnail?.let { thumbnailList.add(it) } }

    println(thumbnailList)
}