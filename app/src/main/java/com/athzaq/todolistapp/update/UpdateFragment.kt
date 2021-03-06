package com.athzaq.todolistapp.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.Delete
import com.athzaq.todolistapp.R
import com.athzaq.todolistapp.data.model.NoteData
import com.athzaq.todolistapp.data.viewMobileData.NotesViewModel
import com.athzaq.todolistapp.databinding.FragmentUpdateBinding
import com.athzaq.todolistapp.fragment.SharedViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private val mSharedViewModels : SharedViewModel by viewModels()
    private val mNotesViewModel: NotesViewModel by viewModels()

    private var _binding : FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        binding.args = args

        binding.spinnerPrioritasUp.onItemSelectedListener = mSharedViewModels.listtener

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_save -> updateItem()
            R.id.menu_delete -> confirmItemRemoval()
        }
        return super.onOptionsItemSelected(item)
    }

    //membuat fun mengconfirm jika ingin di hapus menggunakan alert dialog
    private fun confirmItemRemoval() {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete '${args.currentItem.title}' ?")
            .setMessage("Are You Sure Want To Remove '${args.currentItem.title}'?")
            .setPositiveButton("Yes"){ _, _ ->
                mNotesViewModel.deleteData(args.currentItem)
                Toast.makeText(requireContext(), "Successfully Removed : ${args.currentItem.title}", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }
            .setNegativeButton("No"){_,_ -> }
            .create()
            .show()
    }

    private fun updateItem() {
        val title = binding.edtTextTitleUp.text.toString()
        val description = binding.edtTxtDescUp.text.toString()
        val getPriority = binding.spinnerPrioritasUp.selectedItem.toString()

        val validation = mSharedViewModels.verifyDataFromUser(title, description)
        if (validation) {
            val updateItem = NoteData(
                args.currentItem.id,
                title,
                mSharedViewModels.parsePriority(getPriority),
                description
            )
            mNotesViewModel.updateData(updateItem)
            Toast.makeText(requireContext(), "Berhasil di Update", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Tolong diisi yaa..", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}