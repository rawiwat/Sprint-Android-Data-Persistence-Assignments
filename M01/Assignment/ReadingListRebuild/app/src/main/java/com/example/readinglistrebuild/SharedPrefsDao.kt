package com.example.readinglistrebuild

import android.content.Context
import android.content.SharedPreferences

class SharedPrefsDao {
    companion object {
        const val ID_KEY_LIST = "book list"
        const val NEXT_ID_KEY = "next book"
        const val PREFS_NAME = "my pref"
    }

    private lateinit var sharedPreferences: SharedPreferences

        fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        fun getAllBookIds(): String {
            return sharedPreferences.getString(ID_KEY_LIST, "") ?: ""
        }

        fun getNextId(): String {
            return sharedPreferences.getString(NEXT_ID_KEY, "") ?: ""
        }

    }
}