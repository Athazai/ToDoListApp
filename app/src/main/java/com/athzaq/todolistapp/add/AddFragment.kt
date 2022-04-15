package com.athzaq.todolistapp.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.athzaq.todolistapp.R
import com.athzaq.todolistapp.data.model.NoteData
import com.athzaq.todolistapp.data.viewMobileData.NotesViewModel
import com.athzaq.todolistapp.databinding.FragmentAddBinding
import com.athzaq.todolistapp.fragment.SharedViewModel


class AddFragment : Fragment() {

    private var _addBinding : FragmentAddBinding? = null
    private val addBinding get() = _addBinding!!

    private val noteViewModel : NotesViewModel by viewModels()
    private val sharedViewModel : SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _addBinding = FragmentAddBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        addBinding.spinnerPrioritas.onItemSelectedListener = sharedViewModel.listtener

        return addBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add){
            insertDataToDatabase()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun insertDataToDatabase() {
        val mTitle = addBinding.edtTextTitle.text.toString()
        val mPriority = addBinding.spinnerPrioritas.selectedItem.toString()
        val mDesc = addBinding.edtTxtDesc.text.toString()

        val validation = sharedViewModel.verifyDataFromUser(mTitle, mDesc)
        if (validation) {
            val newData = NoteData(
                0, mTitle, sharedViewModel.parsePriority(mPriority),
                mDesc
            )
            noteViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Berhasil Ditambahkan! ", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Isi ngab", Toast.LENGTH_SHORT).show()
        }
    }
}