package com.example.readinglistrebuild

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun buildItemView(bookView : Book): TextView {
            val textView = TextView(this)
            textView.text = "${bookView.id} : ${bookView.title}"
            textView.setOnClickListener{
                val intent = Intent(this, EditBookActivity::class.java)
                val bookProperty = textView.text
                println(bookProperty)
                intent.putExtra("Selected Book",bookProperty)
                startActivity(intent)
            }

            return textView
        }

        val bookShelf = findViewById<LinearLayout>(R.id.bookScroll)

        val testBook = Book()
        //as the name suggest this is only for testing if the view work or not
        testBook.id = "1"
        testBook.title = "chocolate night"

        bookShelf.addView(buildItemView(testBook))

        findViewById<Button>(R.id.addBookButton).setOnClickListener {
            val intent = Intent(this, EditBookActivity::class.java)
            intent.putExtra("id",bookShelf.childCount)
            startActivity(intent)
        }

    }
}