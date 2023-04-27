package com.example.readinglistrebuild

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.json.JSONException
import org.json.JSONObject
import java.util.*

@Entity
class Book:java.io.Serializable {

     var title:String? = null
     var reasonToRead:String? = null
     var hasBeenRead:Boolean? = null

    @PrimaryKey(autoGenerate = true) @NonNull
     var id:Int = 0

     constructor(){
         this.title = null
         this.reasonToRead = null
         this.hasBeenRead = null
         this.id = 0
     }

     constructor(csvString: String) {
         val values = csvString.split(",")
         this.title = values.elementAtOrNull(0)
         this.reasonToRead = values.elementAtOrNull(1)
         this.hasBeenRead = values.elementAtOrNull(2).toBoolean()
//         this.id = values.elementAtOrNull(3)
     }

    constructor(jsonObject: JSONObject){
        try {
            this.title = jsonObject.getString("title")
        } catch (e:JSONException){
            this.title = ""
        }
        try {
            this.reasonToRead = jsonObject.getString("reasonToRead")
        } catch (e:JSONException){
            this.reasonToRead = ""
        }
        try {
            this.hasBeenRead = jsonObject.getBoolean("hasBeenRead")
        } catch (e:JSONException){
            this.hasBeenRead = false
        }
        try {
            this.id = jsonObject.getInt("id")
        } catch (e:JSONException){
            this.id = 0
        }
    }
     fun toCsvString():String{
         return "$title,$reasonToRead,$hasBeenRead,$id"
     }

     fun toJsonObject(): JSONObject? {
         try {
             return JSONObject().apply {
                 put("title", title)
                 put("reasonToRead",reasonToRead)
                 put("hasBeenRead",hasBeenRead)
                 put("id",id)
             }
         } catch (e1: JSONException) {
             return try {
                 JSONObject("{\"title\" : \"$title\", \"reasonToRead\" : \"$reasonToRead\", \"hasBeenRead\" : \"$hasBeenRead\", \"id\" : \"$id\"}")
             } catch (e2: JSONException){
                 e2.printStackTrace()
                 return null
             }
         }

     }
 }


