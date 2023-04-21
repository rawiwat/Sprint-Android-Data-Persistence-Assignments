package com.example.readinglistrebuild

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
            return textView
        }
        val testBook = Book()
        //as the name suggest this is only for testing if the view work or not
        testBook.id = "1"
        testBook.title = "chocolate night"

        findViewById<LinearLayout>(R.id.bookScroll).addView(buildItemView(testBook))

    }
}