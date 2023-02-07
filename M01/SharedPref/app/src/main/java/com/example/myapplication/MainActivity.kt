package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    companion object{
        const val TIMES_PRESSED_KEY = "Times Pressed"
    }

    var timePressed = 0

    var prefs:SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = getSharedPreferences("Shared Number", Context.MODE_PRIVATE)

        val button = findViewById<Button>(R.id.button)
        val text = findViewById<TextView>(R.id.text)

        timePressed = prefs?.getInt(TIMES_PRESSED_KEY,0)?: 0

        text.text = timePressed.toString()

        button.setOnClickListener{
            timePressed++
            text.text="$timePressed"
            prefs?.let {
                val editor = it.edit()
                editor.putInt("Times Pressed", timePressed)
                editor.commit()
            }
        }
    }
}