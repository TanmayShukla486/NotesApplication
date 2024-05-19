package com.example.notesapp.utils

import android.net.Uri
import androidx.room.TypeConverter

class Converter {
    @TypeConverter
    fun fromList(list: List<Uri>): String{
       return list.joinToString ("--------")
    }

    @TypeConverter
    fun toList(value: String): List<Uri> {
        return value.split("--------").map { uri -> Uri.parse(uri) }
    }
}