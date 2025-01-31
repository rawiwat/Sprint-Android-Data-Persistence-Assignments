package com.example.readinglistrebuild

import android.content.Context
import android.os.Environment
import androidx.lifecycle.LiveData
import org.json.JSONException
import org.json.JSONObject
import java.io.*

class BookStorage(var context: Context) : SaveBookInterface {
    // this class will save book as JSON file
    override fun getAllBookIds(): ArrayList<Int> {
        //gotta access ids from JSON file somehow
        val books = getAllBook()

        val idList = ArrayList<Int>()

        for (book in books){
            book.id?.let {
                idList.add(it)
            }
        }
        return idList
    }

    override fun getNextId(): Int {
        //TODO("Not yet implemented")
        val nextID = getAllBook().size + 1

        return nextID
    }

    override fun getBook(id: Int): String {
        //TODO("Not yet implemented")
        //get an instance of BookList first
        //get book
        //convert JSONObject book to string
        //return the book
        val books = getAllBook()
        val id = id.toInt()
        val book = books.elementAtOrNull(id)//there should be a safer way to do this?

        return book?.toCsvString() ?: ""
    }

    override fun updateBook(book: Book) {
        book.id = getNextId()
        val bookString = book.toJsonObject()
        val filename = "Book${book.title}.json"
        writeToFile(filename,bookString.toString())
    }

    override fun getAllBooks(): LiveData<List<Book>> {
        TODO("Not yet implemented")
    }

    val isExternalStorageWriteable: Boolean
        get() {
            val state = Environment.getExternalStorageState()
            return state == Environment.MEDIA_MOUNTED
        }

    val storageDirectory: File
        get() {
            if (isExternalStorageWriteable) {
                val directory = context.filesDir//Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                return if (!directory.exists() && !directory.mkdir()) {
                    context.cacheDir
                } else {
                    directory
                }
            } else {
                return context.cacheDir
            }
        }

    private fun writeToFile(filename: String, entryString: String) {
            val dir = storageDirectory
            val outputFile = File(dir, filename)

            var writer: FileWriter? = null
            try {
                writer = FileWriter(outputFile)
                writer.write(entryString)
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                if (writer != null) {
                    try {
                        writer.close()
                    } catch (e2: IOException) {
                        e2.printStackTrace()
                    }
                }
            }
         }

    val filelist: ArrayList<String>
        get() {
            val fileNames = arrayListOf<String>()
            val dir = storageDirectory

            val list = dir.list()
            if (list != null) {
                for (name in list) {
                    if (name.contains(".json"))
                        fileNames.add(name)
                }
            }
            return fileNames
        }
    private fun readFromFile(fileName : String):String{
        val inputFile = File(storageDirectory,fileName)
        //val readData = StringBuilder()
        var reader : FileReader? = null
        var readString:String? = null
        try {
            reader = FileReader(inputFile)
            readString = reader.readText()
            /*while (next != -1){
                readData.append(next)
                next = reader.read()
            }*/
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e:IOException){
            e.printStackTrace()
        } finally {
            if (reader != null){
                try {
                    reader.close()
                } catch (e:IOException){
                    e.printStackTrace()
                }
            }
        }
        //return readData.toString()
        return readString?:""
    }

    fun getAllBook():ArrayList<Book>{
        val books = ArrayList<Book>()

        for (filename in filelist){
            val json = readFromFile(filename)
            try {
                books.add(Book(JSONObject(json)))
            } catch (e:JSONException){
                e.printStackTrace()
            }

        }
        return books
    }

}