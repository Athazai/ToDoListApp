package com.athzaq.todolistapp.data.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.athzaq.todolistapp.data.model.NoteData

//perintah-perintah untuk mengakses data di database
//Dao (DATA ACCESS OBJECT) = untuk mengakses
//Query = menjalankan perintah / intruksi untuk sebuah aksi
// fun Dao = mengakses interface kita DAO kita

@Dao
interface NoteDao {
    //read atau menampilkan (R)
    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getDataAll () : LiveData<List<NoteData>>

    //untuk meng-Igrore data yg samaa
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    fun insertData(noteData: NoteData)

    @Update
    fun updateData(noteData: NoteData)

    @Delete
    fun deteleData(noteData: NoteData)

    @Query ("DELETE FROM todo_table")
    fun deleteAllData()

    //search by key
    @Query ("SELECT * FROM todo_table WHERE title LIKE :searchQuery")
    fun searchDatabase (searchQuery : String) : LiveData <List<NoteData>>

    //menampilkan data dari high ke low
    @Query ("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    fun sortByHighPriority() : LiveData<List<NoteData>>

    //menampilkan data dari low ke high
    @Query ("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    fun sortByLowPriority() : LiveData<List<NoteData>>

}