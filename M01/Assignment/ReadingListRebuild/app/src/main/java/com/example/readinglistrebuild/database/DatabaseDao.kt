package com.example.readinglistrebuild.database

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.readinglistrebuild.Book

interface DatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createBook(book: Book)

    @Query("SELECT * FROM book")
    fun getAllBooks():ArrayList<Book>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateBook(book: Book)

    @Delete
    fun deleteEntry(book: Book)
}