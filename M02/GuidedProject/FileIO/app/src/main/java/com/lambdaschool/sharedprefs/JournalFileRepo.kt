package com.lambdaschool.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Environment
import com.lambdaschool.sharedprefs.model.JournalEntry
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException

// TODO 3: Implement the interface here
class JournalFileRepo(var context: Context): JournalRepoInterface {

    // Basic structure: We will save each object to its own json file

    // TODO 6: createEntry implementation
    override fun createEntry(entry: JournalEntry) {
        val entryString = entry.toJsonObject()
        val filename = "JournalEntry${entry.date}.json"
        writeToFile(filename, entryString.toString())
    }

    override fun readAllEntries(): MutableList<JournalEntry> {

        val entries = ArrayList<JournalEntry>()

        for (filename in filelist){
            val json = readFromFile(filename)
            try {
                entries.add(JournalEntry(JSONObject(json)))
            } catch (e:JSONException){
                e.printStackTrace()
            }
        }
        return entries
    }

    override fun updateEntry(entry: JournalEntry) {
        //TODO("Not yet implemented")
        createEntry(entry)
    }

    override fun deleteEntry(entry: JournalEntry) {
        TODO("Not yet implemented")
    }

    // TODO 8: writeToFile helper
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

    // TODO 9: Save storage directory as a member variable
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

    // TODO 10: Check for external storage is writeable
    val isExternalStorageWriteable: Boolean
        get() {
            val state = Environment.getExternalStorageState()
            return state == Environment.MEDIA_MOUNTED
        }
    // TODO 11: readAllEntries implementation

    // TODO 12: Save fileList as a member variable
    val filelist: ArrayList<String>
        get() {
            val fileNames = arrayListOf<String>()
            val dir = storageDirectory

            val list = dir.list()
            if (list != null) {
                for (name in list) {
                    if (name.contains(".json"))
                        fileNames.add(name)
                }
            }
            return fileNames
        }
    // TODO 13: readFromFile helper
    private fun readFromFile(fileName : String):String{
        val inputFile = File(storageDirectory,fileName)
        //val readData = StringBuilder()
        var reader : FileReader? = null
        var readString:String? = null
        try {
            reader = FileReader(inputFile)
            readString = reader.readText()
            /*while (next != -1){
                readData.append(next)
                next = reader.read()
            }*/
        } catch (e:FileNotFoundException) {
            e.printStackTrace()
        } catch (e:IOException){
            e.printStackTrace()
        } finally {
            if (reader != null){
                try {
                    reader.close()
                } catch (e:IOException){
                    e.printStackTrace()
                }
            }
        }
        //return readData.toString()
        return readString?:""
    }
    // TODO 14: updateEntry implementation

    // TODO 15: deleteEntry implementation

}
