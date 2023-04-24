package com.example.readinglistrebuild

import android.content.Context

class BooksModel(context: Context) {

    private val sharedPrefsDao: SharedPrefsDao = SharedPrefsDao()

    init {
        this.sharedPrefsDao.initialize(context)
    }

    fun getListOfBook():ArrayList<Book>{
        val bookIdlist = sharedPrefsDao.getAllBookIds()
        //gotta access all the books from their list of ID later
        val bookList = ArrayList<Book>()
        val bookCSVList = ArrayList<String?>()
        for (id in bookIdlist){
            bookCSVList.add(sharedPrefsDao.getBook(id))
        }

        for (bookCSV in bookCSVList){
            val bookProperty = bookCSV?.split(",")
            val aBook = Book()
            aBook.title = bookProperty?.elementAtOrNull(0)
            aBook.reasonToRead = bookProperty?.elementAtOrNull(1)
            aBook.hasBeenRead = bookProperty?.elementAtOrNull(2).toBoolean()
            aBook.id = bookProperty?.elementAtOrNull(3)

            bookList.add(aBook)
        }

         return bookList
    }

    fun getBookFromId(id:String):Book{
        //val sharedPrefsDao = SharedPrefsDao()
        val book = sharedPrefsDao.getBook(id)
        val bookProperty = book?.split(",")
        val bookfromID = Book()
        bookfromID.title = bookProperty?.get(0)
        bookfromID.reasonToRead = bookProperty?.get(1)
        bookfromID.hasBeenRead  = bookProperty?.get(2).toBoolean()
        bookfromID.id = id

        return bookfromID
    }

    fun getNextID():Int{
        val nextID = sharedPrefsDao.getNextId()

        return nextID
    }

    fun updateBook(book: Book){
        sharedPrefsDao.updateBook(book)
    }
}