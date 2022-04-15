package com.athzaq.todolistapp.data

import android.content.Context
import androidx.room.*
import com.athzaq.todolistapp.data.model.NoteData
import com.athzaq.todolistapp.data.repository.NoteDao

//Database harus abstrak class
//entities = mendaftarkan nama class kita (NoteData)


@Database(entities = [NoteData::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao() : NoteDao

    companion object {
        @Volatile
        private var INSTANCE : NoteDatabase? = null
        fun getDatabase(context: Context) : NoteDatabase {
            val tempInstace = INSTANCE
            if (tempInstace != null){
                return tempInstace
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"

                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}