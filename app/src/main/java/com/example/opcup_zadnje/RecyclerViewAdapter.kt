package com.example.opcup_zadnje

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

enum class NoteClickType { REMOVE }

class RecyclerViewAdapter (
    val notes: ArrayList<Note>,
    val listener: ContentListener
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_note, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NoteViewHolder -> {
                holder.bind(position, listener, notes[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun removeNote(index: Int) {
        notes.removeAt(index)
        notifyItemRemoved(index)
        notifyItemRangeChanged(index, notes.size)
    }


    fun addNote(note: Note){
        notes.add(itemCount,note)
        notifyItemInserted(itemCount)
    }

    class NoteViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        private val companyName = view.findViewById<TextView>(R.id.textViewCompanyName)
        private val companyAddress = view.findViewById<TextView>(R.id.textViewCompanyAddress)
        private val guilty = view.findViewById<TextView>(R.id.textViewGuilty)
        private val deleteBtn = view.findViewById<ImageButton>(R.id.deleteButton)

        fun bind(
            index: Int,
            listener: ContentListener,
            note: Note
        ) {
            companyName.text = note.name
            companyAddress.text = note.address
            guilty.text = note.guilty

            deleteBtn.setOnClickListener {
                listener.onNoteButtonClick(index, note, NoteClickType.REMOVE)
            }
        }
    }

    interface ContentListener {
        fun onNoteButtonClick(index: Int, note: Note, clickType: NoteClickType)
    }
}