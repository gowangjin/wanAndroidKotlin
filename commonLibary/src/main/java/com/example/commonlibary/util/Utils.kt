package com.example.commonlibary.util

object Utils {
    fun clearHighlightFormat(str : String):String{
        var newString = str
        if(str.contains("<em class='highlight'>")){
            newString = str.replace("<em class='highlight'>"," ")
        }
        if(newString.contains("</em>")){
            newString = newString.replace("</em>"," ")
        }
         return newString
    }
}