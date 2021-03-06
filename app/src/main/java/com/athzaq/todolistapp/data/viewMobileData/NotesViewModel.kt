package com.athzaq.todolistapp.data.viewMobileData

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.athzaq.todolistapp.data.NoteDatabase
import com.athzaq.todolistapp.data.model.NoteData
import com.athzaq.todolistapp.data.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val noteDao = NoteDatabase.getDatabase(application).noteDao()
    private val repository : NoteRepository
    val getAllData : LiveData<List<NoteData>>
    val sortByHighPriority : LiveData<List<NoteData>>
    val sortByLowPriority : LiveData<List<NoteData>>

    init {
        repository = NoteRepository(noteDao)
        getAllData = repository.getAllData
        sortByHighPriority = repository.shortByHighPriority
        sortByLowPriority = repository.shortByLowPriority
    }

    fun insertData(noteData: NoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(noteData)
        }
    }

    fun updateData(noteData: NoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(noteData)
        }
    }

    fun deleteData(noteData: NoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteData(noteData)
        }
    }

    fun deleteAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllData()
        }
    }

    fun searchDatabase(searchQuery : String) : LiveData<List<NoteData>> {
        return  repository.searchData(searchQuery)
    }

}