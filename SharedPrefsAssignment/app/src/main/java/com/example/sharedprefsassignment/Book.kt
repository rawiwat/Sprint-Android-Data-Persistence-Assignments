package com.example.sharedprefsassignment

class Book {
    var title:String? = null
    var reasonToRead:String? = null
    var hasBeenRead:Boolean? = null
    var id:String? = null

    constructor( title:String,
                 reasonToRead:String,
                 hasBeenRead:Boolean,
                 id:String) {
    }
    fun toCSVString():String{
        return "$title,$reasonToRead,$hasBeenRead,$id"
    }

    constructor(csvString: String){

    }
}
