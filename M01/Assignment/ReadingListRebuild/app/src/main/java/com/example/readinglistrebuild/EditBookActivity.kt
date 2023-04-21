package com.example.readinglistrebuild

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText

class EditBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_book)

        val iD = getIntent().getStringExtra("id")
        val selectedBook = getIntent().getStringExtra("Selected Book")

        println(selectedBook)

        val selectedBookProperty = selectedBook?.split(":")
        val bookTitle = findViewById<EditText>(R.id.bookName)
        val selectedBookTitle = Editable.Factory.getInstance().newEditable(selectedBookProperty?.get(0))
        val bookReasonToRead = findViewById<EditText>(R.id.bookReasonToRead)
        val selectedBookReasonToRead = Editable.Factory.getInstance().newEditable(selectedBookProperty?.get(1))

        bookTitle.text = selectedBookTitle
        bookReasonToRead.text = selectedBookReasonToRead

    }
}