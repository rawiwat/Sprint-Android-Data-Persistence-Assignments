package com.example.readinglistrebuild.database

import android.content.Context
import com.example.readinglistrebuild.Book
import com.example.readinglistrebuild.SaveBookInterface

class DatabaseRepo(context: Context): SaveBookInterface {
    override fun getAllBookIds(): ArrayList<String> {
        TODO("Not yet implemented")
    }

    override fun getNextId(): Int {
        TODO("Not yet implemented")
    }

    override fun getBook(id: String): String? {
        TODO("Not yet implemented")
    }

    override fun updateBook(book: Book) {
        TODO("Not yet implemented")
    }
}