package com.athzaq.todolistapp.data

import androidx.room.TypeConverter
import com.athzaq.todolistapp.data.model.Prority

class Converter {

    //berfungsi untuk mengconvert
    @TypeConverter
    //data dari ProrityClass di ubah ke String
    fun fromPriority(prority: Prority) : String {
        return prority.name
    }

    @TypeConverter
    fun toPriority(prority: String) : Prority {
        return Prority.valueOf(prority)
    }

}