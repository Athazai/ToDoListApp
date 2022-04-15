package com.athzaq.todolistapp.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.athzaq.todolistapp.data.model.NoteData
import com.athzaq.todolistapp.databinding.RowItemBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    var dataList = emptyList<NoteData>()
    class ViewHolder(val binding : RowItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(noteData: NoteData) {
            binding.noteData = noteData
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup) : ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = dataList.size

    fun setData(noteData: List<NoteData>) {
        val noteDiffUtil = NoteDiffUtil(dataList, noteData)
        val noteDiffUtilResult = DiffUtil.calculateDiff(noteDiffUtil)
        this.dataList = noteData
        noteDiffUtilResult.dispatchUpdatesTo(this)
    }
}