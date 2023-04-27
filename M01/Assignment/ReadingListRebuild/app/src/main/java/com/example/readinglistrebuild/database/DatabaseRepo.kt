package com.example.readinglistrebuild.database

import android.content.Context
import androidx.room.Room
import com.example.readinglistrebuild.Book
import com.example.readinglistrebuild.SaveBookInterface

class DatabaseRepo(context: Context): SaveBookInterface {
    val context = context.applicationContext
    override fun getAllBookIds(): ArrayList<Int> {
        val books = database.bookDao().getAllBooks()
        val allBookIds = ArrayList<Int>()
        for (book in books){
            book.id.let {
                allBookIds.add(it)
            }
        }
        return allBookIds
    }

    override fun getNextId(): Int {
        val nextId = database.bookDao().getAllBooks().size +1

        return nextId
    }

    override fun getBook(id: String): String? {
        val books = database.bookDao().getAllBooks()
        val id = id.toInt()
        val book = books.elementAtOrNull(id)//there should be a safer way to do this?

        return book?.toCsvString() ?: ""
    }

    override fun updateBook(book: Book) {
        database.bookDao().updateBook(book)
    }

    private val database by lazy {
        Room.databaseBuilder(
            context,
            BookDatabase::class.java,"entry_database"
        ).fallbackToDestructiveMigration().build()
    }
}