package com.example.readinglistrebuild

import android.content.Context

interface SaveBookInterface {
    fun getAllBookIds() : ArrayList<Int>
    fun getNextId():Int
    fun getBook(id: String): String?
    fun updateBook(book: Book)
}