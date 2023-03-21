package com.example.sharedprefsassignment

import android.content.Intent
import android.net.ipsec.ike.TunnelModeChildSessionParams.ConfigRequestIpv6DnsServer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class MainActivity : AppCompatActivity() {

    lateinit var newBookTitle:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val booklist = findViewById<FrameLayout>(R.id.listOfBook)

        fun buildItemView (){
            //this is only for step 3-4 in Part 1 which test the method by hard coding it in practice you get the value of book when this was call
            val newBook = Book("huzzah","nothin",true,"1")
            newBookTitle.text = newBook.title
            //val constraint = ConstraintSet()
            val parameter = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
            newBookTitle.id = booklist.childCount
            newBookTitle.layoutParams = parameter
            println(newBookTitle.id)
            booklist.addView(newBookTitle)
            newBookTitle.setOnClickListener {
                val intent = Intent(this,BookActivity::class.java)
                intent.putExtra("",String())
                startActivity(intent)
            }


            println(newBookTitle.text)
        }

        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener{
            val intent = Intent(this,BookActivity::class.java)
            intent.putExtra("new id",String())
            startActivity(intent)
            buildItemView()
        }
        //buildItemView()
    }
}