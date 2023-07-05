package com.chen.mylibrary

import com.google.gson.Gson

class MyUtils {

    public fun getObject(str:String):Any{
        val gson = Gson()
       return gson.fromJson<Any>(str,Any::class.java)
    }
}