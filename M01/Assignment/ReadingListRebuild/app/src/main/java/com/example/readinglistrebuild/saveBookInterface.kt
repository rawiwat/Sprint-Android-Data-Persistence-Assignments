package com.example.readinglistrebuild

import android.content.Context
import androidx.lifecycle.LiveData

interface SaveBookInterface {
    fun getAllBookIds() : ArrayList<Int>
    fun getNextId():Int
    fun getBook(id: Int): String?
    fun updateBook(book: Book)

    fun getAllBooks() : LiveData<List<Book>>
}