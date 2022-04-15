package com.athzaq.todolistapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "todo_table")
@Parcelize
class NoteData (

        @PrimaryKey(autoGenerate = true)

        val id : Int,
        val title :String,
        val priority : Prority,
        val description : String

) : Parcelable