package com.example.sharedprefsassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Book : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

    }

    override fun onBackPressed() {

        finish()
    }
}