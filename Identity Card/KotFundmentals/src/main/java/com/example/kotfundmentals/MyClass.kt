package com.example.kotfundmentals

fun main() {

    val finder = Finder<String>(mutableListOf<String>("SHAM", "ALAA", "MANAL"))
    finder.findElementIndex("") { index ->
        println("Item Found At Index:$index")
    }


}


open class Person(id: Int, name: String) {

    val id: Int = id
    val name: String = name

    open fun walk() {

    }
}

class Employee(id: Int, name: String) : Person(id, name), EventHandler {

    override fun walk() {
        super.walk()

        println("This is an Override")
    }

    override fun onClick(event: String) {
        TODO("Not yet implemented")
    }
}

interface EventHandler {
    fun onClick(event: String);
}

// Extension Function
fun Person.toJson(): Map<String, Any> {
    return mapOf(Pair("id", this.id), Pair("name", this.name))
}

fun String.removeFirstAndLastChart(): String {
    return this.dropLast(1).drop(1)
}

class Finder<T>(private val list: List<T>) {

    fun findElementIndex(item: T, onItemFound: (Int?) -> Unit) {
        val filteredData = list.filter {
            it == item
        }
        if (filteredData.isEmpty()) {
            onItemFound(null);
        } else {
            onItemFound(list.indexOf(item))
        }
    }
}


//enum class Response {
//    SUCCESS,
//    FAILURE,
//    PENDING
//}

abstract class Response

data class Success(val dataFetched: String?) : Response()
data class Failure(val exception: Exception?) : Response()
object Loading : Response()
object NotLoading : Response()

object Repository {
    private var currentState: Response = NotLoading
    private var fetchedData: String? = ""

    fun fetchData() {
        currentState = Loading
        fetchedData = "Data"
    }

    fun finishFetch() {
        currentState = Success(dataFetched = fetchedData)
        fetchedData = null
    }

    fun throwError() {
        currentState = Failure(exception = Exception("Error"))
        fetchedData=null
    }
}