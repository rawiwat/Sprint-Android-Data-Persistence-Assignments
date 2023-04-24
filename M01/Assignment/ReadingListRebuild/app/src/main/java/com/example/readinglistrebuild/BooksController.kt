package com.example.readinglistrebuild

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.LinearLayout
import android.widget.TextView

class BooksController {

    fun getBookViews(context: Context): LinearLayout {
        val books = BooksModel(context).getListOfBook()
        var bookShelf = LinearLayout(context)
        for (book in books) {
            val textView = getBooksView(book, context)
            bookShelf.addView(textView)
        }

        return bookShelf
    }

    fun getBooksView(book: Book, context: Context): TextView {
        val textView = TextView(context)
        textView.text = "${book.title},${book.reasonToRead},${book.hasBeenRead},${book.id}"
        textView.setOnClickListener{
//            val intent = Intent(context, EditBookActivity::class.java)
            val bookProperty = textView.text
            println(bookProperty)
//            intent.putExtra("Selected Book",bookProperty)
//            setResult(Activity.RESULT_OK)
//            startActivityForResultorResult(intent, 2)
        }
        return textView
    }
}