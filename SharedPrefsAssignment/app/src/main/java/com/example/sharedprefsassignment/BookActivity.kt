package com.example.sharedprefsassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class BookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        getIntent().getStringExtra("new id")
    }

    override fun onBackPressed() {

        finish()
    }
}