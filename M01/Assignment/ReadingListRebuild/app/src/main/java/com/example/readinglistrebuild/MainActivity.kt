package com.example.readinglistrebuild

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.example.readinglistrebuild.database.DatabaseRepo
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {

    private var returnedBook:String? = null
    private var bookShelf:LinearLayout? = null
    private val booksController = BooksController()
    lateinit var viewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bookShelf = findViewById<LinearLayout>(R.id.bookScroll)
        viewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

        findViewById<Button>(R.id.addBookButton).setOnClickListener {
            val intent = Intent(this, EditBookActivity::class.java)
            intent.putExtra("id",bookShelf?.childCount)
            startActivityForResult(intent, 1)
        }
//
//        println("got $returnedBook")
//
////            val intent = Intent(applicationContext, EditBookActivity::class.java)
////    val bookProperty = textView.text
////            intent.putExtra("Selected Book",bookProperty)
////            setResult(Activity.RESULT_OK)
////            startActivityForResultorResult(intent, 2)

            //updateBookshelf()
        ReadAllEntriesAsyncTask(this).execute()

    }

    private fun updateLayout(entries: List<Book>) {
        bookShelf?.removeAllViews()
        entries.forEach { book ->
            bookShelf?.addView(booksController.getBooksView(book,applicationContext))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        println("$requestCode,$resultCode")
        if (requestCode == 1 && resultCode == RESULT_OK){
            val newBook = data?.getStringExtra("returnedBook")
            println(newBook)
            returnedBook = newBook


            newBook?.let {
                val book = Book(newBook)
                //booksController.updateBook(book, applicationContext)
                //updateBookshelf()
                UpdateBookAsyncTask(viewModel).execute(book)
            }
        }

    }

    fun updateBookshelf(){
        bookShelf?.let {
            val bookShelfLayout = booksController.getBookViews(applicationContext, it)
        }
    }

    class UpdateBookAsyncTask(val viewModel: BookViewModel) : AsyncTask<Book,Void,Unit>() {
        override fun doInBackground(vararg params: Book?) {
            if (params.isNotEmpty()){
                params[0]?.let {
                    viewModel.updateBook(it)
                }
            }
        }
    }

    class ReadAllEntriesAsyncTask(activity:MainActivity) :
        AsyncTask<Void, Void, LiveData<List<Book>>>() {
        val actRef = WeakReference(activity)
        override fun doInBackground(vararg voids: Void): LiveData<List<Book>>? {
            return actRef.get()?.viewModel?.books
        }
        override fun onPostExecute(result: LiveData<List<Book>>?) {
            result?.let{
                actRef.get()?.let { act ->
                    result.observe(act, Observer<List<Book>>{t ->
                        t?.let {
                            act.updateLayout(t)
                        }
                    })
                }
            }
        }
    }

}