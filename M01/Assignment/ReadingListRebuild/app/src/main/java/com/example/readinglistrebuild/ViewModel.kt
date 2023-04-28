package com.example.readinglistrebuild

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class BookViewModel : ViewModel() {
    val books : LiveData<List<Book>> by lazy {
        getAllBooks()
    }
    fun getAllBookIds():ArrayList<Int>{
        return repo.getAllBookIds()
    }

    fun getAllBooks():LiveData<List<Book>>{
        return repo.getAllBooks()
    }
    fun updateBook(book: Book){
        repo.updateBook(book)
    }

}