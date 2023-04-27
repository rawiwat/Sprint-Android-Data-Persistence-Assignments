package com.example.readinglistrebuild.database

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.readinglistrebuild.Book

interface DatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createBook(book: Book)

    @Query("SELECT * FROM book")
    fun getAllBooks():LiveData<List<Book>>

}