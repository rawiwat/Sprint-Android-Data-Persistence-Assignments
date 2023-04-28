package com.example.readinglistrebuild.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.readinglistrebuild.Book
import com.example.readinglistrebuild.SaveBookInterface

class DatabaseRepo(context: Context): SaveBookInterface {
    val context = context.applicationContext
    override fun getAllBookIds(): ArrayList<Int> {
        val booksLiveData = database.bookDao().getAllBooks()
        val allBookIds = ArrayList<Int>()
        val books : ArrayList<Book> = ArrayList(booksLiveData.value ?: emptyList())

        for (book in books){
            book.id.let {
                allBookIds.add(it)
            }
        }
        return allBookIds
    }

    override fun getNextId(): Int {
        val books = bookLiveDataToBookList()
        val nextId = books.size + 1

        return nextId
    }

    override fun getBook(id: Int): String {
        val books = bookLiveDataToBookList()
        val id = id
        val book = books.elementAtOrNull(id)//there should be a safer way to do this?

        return book?.toCsvString() ?: ""
    }

    override fun updateBook(book: Book) {
        database.bookDao().updateBook(book)
    }

    override fun getAllBooks(): LiveData<List<Book>> {
        return database.bookDao().getAllBooks()
    }

    private fun bookLiveDataToBookList():ArrayList<Book>{
        val booksLiveData = database.bookDao().getAllBooks()
        val books : ArrayList<Book> = ArrayList(booksLiveData.value ?: emptyList())

        return books
    }

    private val database by lazy {
        Room.databaseBuilder(
            context,
            BookDatabase::class.java,"entry_database"
        ).fallbackToDestructiveMigration().build()
    }
}