package com.example.readinglistrebuild

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

class EditBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_book)

        //val iD = getIntent().getStringExtra("id")
        var hasBeenRead = false
        val checkButton = findViewById<CheckBox>(R.id.readCheck)

        findViewById<Button>(R.id.summitButton).setOnClickListener {
            returnData()
            finish()
        }

        findViewById<Button>(R.id.cancelButton).setOnClickListener {
            val cancelEdit = Intent()
            setResult(Activity.RESULT_CANCELED,cancelEdit)
            finish()
        }

            checkButton.setOnClickListener {
            hasBeenRead = !hasBeenRead
                //(hasBeenRead.toString())
        }
        checkMainActivityResult(2, RESULT_OK,intent)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        returnData()
    }

    private fun returnData() {
        val editedTitle = findViewById<EditText>(R.id.bookName).text
        val editedReasonToRead = findViewById<EditText>(R.id.bookReasonToRead).text
        val editedHasBeenRead = findViewById<CheckBox>(R.id.readCheck)
        val bookCSV = "${editedTitle},${editedReasonToRead},${editedHasBeenRead}"
        val returnedData = Intent()
        returnedData.putExtra("returnedBook",bookCSV)
        setResult(Activity.RESULT_OK,returnedData)
    }

    companion object {
        const val REQUEST_CODE_EDITBOOK = 2
    }
    fun checkMainActivityResult (requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_EDITBOOK && resultCode == RESULT_OK){
            val selectedBook = data?.getStringExtra("Selected Book")
            val selectedBookProperty = selectedBook?.split(",")
            if(selectedBookProperty != null){
                val bookTitle = findViewById<EditText>(R.id.bookName)
                val selectedBookTitle = Editable.Factory.getInstance().newEditable(selectedBookProperty?.get(0))
                val bookReasonToRead = findViewById<EditText>(R.id.bookReasonToRead)
                val selectedBookReasonToRead = Editable.Factory.getInstance().newEditable(selectedBookProperty?.get(1))

                bookTitle.text = selectedBookTitle
                bookReasonToRead.text = selectedBookReasonToRead
            }
        }
    }
}