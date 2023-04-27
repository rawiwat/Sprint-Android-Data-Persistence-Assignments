package com.example.readinglistrebuild.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.readinglistrebuild.Book

@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class BookDatabase : RoomDatabase(){
    abstract fun bookDao() : DatabaseDao
}