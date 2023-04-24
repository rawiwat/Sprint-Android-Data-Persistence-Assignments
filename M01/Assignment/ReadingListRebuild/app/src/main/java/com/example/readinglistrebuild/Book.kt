package com.example.readinglistrebuild

import org.json.JSONException
import org.json.JSONObject

class Book:java.io.Serializable {

     var title:String? = null
     var reasonToRead:String? = null
     var hasBeenRead:Boolean? = null
     var id:String? = null

     constructor(){
         this.title = null
         this.reasonToRead = null
         this.hasBeenRead = null
         this.id = null
     }

     constructor(csvString: String) {
         val values = csvString.split(",")
         this.title = values.elementAtOrNull(0)
         this.reasonToRead = values.elementAtOrNull(1)
         this.hasBeenRead = values.elementAtOrNull(2).toBoolean()
//         this.id = values.elementAtOrNull(3)
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