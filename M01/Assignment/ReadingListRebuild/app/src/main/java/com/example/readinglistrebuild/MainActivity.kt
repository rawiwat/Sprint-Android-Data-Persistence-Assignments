package com.example.readinglistrebuild

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var returnedBook:String? = null
    private var bookShelf:LinearLayout? = null
    private val sharedPreferences: SharedPrefsDao = SharedPrefsDao()
    private val booksController = BooksController()

    val PREFS_NAME = "my pref"

//    fun buildItemView(bookView : Book): TextView {
//        val textView = TextView(this)
//        textView.text = "${bookView.title},${bookView.reasonToRead},${bookView.hasBeenRead},${bookView.id}"
//        textView.setOnClickListener{
//            val intent = Intent(this, EditBookActivity::class.java)
//            val bookProperty = textView.text
//            println(bookProperty)
//            intent.putExtra("Selected Book",bookProperty)
//            setResult(Activity.RESULT_OK)
//            startActivityForResult(intent, 2)
//        }
//
//        return textView
//    }
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bookShelf = findViewById<LinearLayout>(R.id.bookScroll)

//        val testBook = Book()
//        //as the name suggest this is only for testing if the view work or not
//        testBook.id = "1"
//        testBook.title = "chocolate night"

//        bookShelf?.addView(buildItemView(testBook))

        findViewById<Button>(R.id.addBookButton).setOnClickListener {
            val intent = Intent(this, EditBookActivity::class.java)
            intent.putExtra("id",bookShelf?.childCount)
            startActivityForResult(intent, 1)
        }

        println("got $returnedBook")
        updateBookshelf()

        val bookShelfLayout = booksController.getBookViews(applicationContext)

        bookShelf?.addView(bookShelfLayout)

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        println("$requestCode,$resultCode")
        if (requestCode == 1 && resultCode == RESULT_OK){
        val newBook = data?.getStringExtra("returnedBook")
            println(newBook)
            returnedBook = newBook

            updateBookshelf()

            newBook?.let {
                val book = Book(newBook)
                // save book in shared prefereced
                sharedPreferences.updateBook(book)
            }
        }

    }

    fun updateBookshelf(){
        if(returnedBook != null) {
            val newBookProperty = returnedBook?.split(",")
            val newBook = Book()
            if (newBookProperty != null) {
                newBook.title = newBookProperty.get(0)
                newBook.reasonToRead = newBookProperty.get(1)
                newBook.hasBeenRead = newBookProperty.get(2).toBoolean()
                newBook.id = newBookProperty.get(3)
            }
//            bookShelf?.addView(buildItemView(newBook))
        }
    }

}