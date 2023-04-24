package com.example.readinglistrebuild

import android.content.Context

interface saveBookInterface {
    fun getAllBookIds() : ArrayList<String>
    fun getNextId():Int
    fun getBook(id: String): String?
    fun updateBook(book: Book)
}