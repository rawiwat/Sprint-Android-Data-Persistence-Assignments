package com.example.readinglistrebuild

 class Book {

     var title:String? = null
     val reasonToRead:String? = null
     val hasBeenRead:Boolean? = null
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