package com.example.readinglistrebuild

import android.content.Context
import android.os.Environment
import java.io.File
import java.io.FileWriter
import java.io.IOException

class BookStorage(var context: Context) : saveBookInterface {
    // this class will save book as JSON file
    override fun getAllBookIds(): ArrayList<String> {
        TODO("Not yet implemented")
    }

    override fun getNextId(): Int {
        TODO("Not yet implemented")
    }

    override fun getBook(id: String): String? {
        TODO("Not yet implemented")
    }

    override fun updateBook(book: Book) {
        //TODO("Not yet implemented")
        val bookString = book.toJsonObject()
        val filename = "Book${book.title}.json"
        writeToFile(filename,bookString.toString())
    }

    val isExternalStorageWriteable: Boolean
        get() {
            val state = Environment.getExternalStorageState()
            return state == Environment.MEDIA_MOUNTED
        }

    val storageDirectory: File
        get() {
            if (isExternalStorageWriteable) {
                val directory = context.filesDir//Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                return if (!directory.exists() && !directory.mkdir()) {
                    context.cacheDir
                } else {
                    directory
                }
            } else {
                return context.cacheDir
            }
        }

    private fun writeToFile(filename: String, entryString: String) {
            val dir = storageDirectory
            val outputFile = File(dir, filename)

            var writer: FileWriter? = null
            try {
                writer = FileWriter(outputFile)
                writer.write(entryString)
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                if (writer != null) {
                    try {
                        writer.close()
                    } catch (e2: IOException) {
                        e2.printStackTrace()
                    }
                }
            }
         }

}