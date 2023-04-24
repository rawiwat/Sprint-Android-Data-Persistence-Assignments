package com.example.readinglistrebuild

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

 }