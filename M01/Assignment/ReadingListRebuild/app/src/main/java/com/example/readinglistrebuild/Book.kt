package com.example.readinglistrebuild

 class Book:java.io.Serializable {

     var title:String? = null
     var reasonToRead:String? = null
     var hasBeenRead:Boolean? = null
     var id:String? = null

     constructor(){
         this.title
         this.reasonToRead
         this.hasBeenRead
         this.id
     }

     constructor(csvString: String){
         val values = csvString.split(",")
     }

     fun toCsvString():String{
         return "$title,$reasonToRead,$hasBeenRead,$id"
     }

 }