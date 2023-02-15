package com.example.sharedprefsassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val booklist = findViewById<FrameLayout>(R.id.listOfBook)

        fun buildItemView (){
            //this is only for step 3-4 in Part 1 which test the method by hard coding it
            val newBook = Book("huzzah","nothin",true,"1")
            val newBookTitle = TextView(this)
            newBookTitle.text = newBook.title
            booklist.addView(newBookTitle)
        }

        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener{
            //val intent = Intent(this,BookActivity::class.java)
            //startActivity(intent)
            buildItemView()
        }
    }
}