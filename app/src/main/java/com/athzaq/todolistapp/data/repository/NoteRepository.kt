package com.athzaq.todolistapp.data.repository

import androidx.lifecycle.LiveData
import com.athzaq.todolistapp.data.model.NoteData

class NoteRepository(private val noteDao: NoteDao) {

    val getAllData : LiveData<List<NoteData>> = noteDao.getDataAll()
    val shortByHighPriority : LiveData<List<NoteData>> = noteDao.sortByHighPriority()
    val shortByLowPriority : LiveData<List<NoteData>> = noteDao.sortByLowPriority()

    fun insertData(noteData: NoteData){
        noteDao.insertData(noteData)
    }

    fun updateData(noteData: NoteData) {
        noteDao.updateData(noteData)
    }

    fun deleteData(noteData: NoteData) {
        noteDao.deteleData(noteData)
    }

    fun deleteAllData() {
        noteDao.deleteAllData()
    }

    fun searchData(searchQuery: String) : LiveData<List<NoteData>> {
        return noteDao.searchDatabase(searchQuery)
    }
}