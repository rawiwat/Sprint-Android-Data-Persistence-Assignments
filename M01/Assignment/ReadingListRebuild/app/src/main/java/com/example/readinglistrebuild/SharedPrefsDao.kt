package com.example.readinglistrebuild

import android.content.Context
import android.content.SharedPreferences

class SharedPrefsDao: saveBookInterface {
    companion object {
        const val ID_KEY_LIST = "book list"
        const val NEXT_ID_KEY = "next book"
        const val PREFS_NAME = "my pref"
        private const val BOOK_ID_KEY_PREFIX = "book_"
    }

    private lateinit var sharedPreferences: SharedPreferences

        fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    override fun getAllBookIds(): ArrayList<String> {
        // "1,2,3,4,5" -> ["1", "2", ...]

        val idList = sharedPreferences.getString(ID_KEY_LIST, "") ?: ""

        val arr = idList.split(",").toTypedArray()
        val list = ArrayList<String>(arr.asList())

        return list
    }

    override fun getNextId(): Int {
        return sharedPreferences.getInt(NEXT_ID_KEY, 0) ?: 0
    }

    override fun getBook(id: String): String? {
        // "book_"  "5"
        // "book_5"
        val key = BOOK_ID_KEY_PREFIX + id
        val bookCSV = sharedPreferences.getString(key, "")
        return bookCSV
    }

    override fun updateBook(book: Book) {
        // 1,2,3,4,5
        val ids = getAllBookIds()
        // 1
        val bookID = book.id ?: ""

        if (!bookID.isEmpty() && ids.contains(bookID)) {
            //update
            //since this book ID already exist in list of books there's no need to make a new
            //get an instance of a book with this ID
            //put updated properties of this book into sharedpref
            //then commit


        } else {
            //save new book
            val editor = sharedPreferences.edit()
            var newIDList = getAllBookIds()
            // 1,2,3,4
            //bookID=5
//                newIDList.add(bookID)
            //1,2,3,4, 5

            // 5
            var nextID = getNextId()
            // bookid = 5
            book.id = nextID.toString()

            val newID = nextID + 1
            editor.putInt(NEXT_ID_KEY, newID)

            // [1,2,3,4, 5]
            newIDList.add(nextID.toString())

            editor.putString(ID_KEY_LIST, newIDList.joinToString(separator = ","))

            // "book_5" :  "5, 2.3.23, 2, sdfsdfdsfdsfd, sdflkjdsf"
            val bookProperty = book.toCsvString()

            editor.putString(BOOK_ID_KEY_PREFIX + nextID, bookProperty)
            editor.commit()
        }

    }
}
// sharedprefdao
// save(book)
// get(book)