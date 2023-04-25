package com.example.readinglistrebuild

import android.content.Context

interface SaveBookInterface {
    fun getAllBookIds() : ArrayList<String>
    fun getNextId():Int
    fun getBook(id: String): String?
    fun updateBook(book: Book)
}