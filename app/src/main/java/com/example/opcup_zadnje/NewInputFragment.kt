package com.example.opcup_zadnje

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class NewInputFragment : Fragment(),RecyclerViewAdapter.ContentListener {

    private val db = Firebase.firestore
    private lateinit var myAdapter: RecyclerViewAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_input, container, false)

        val prevBtn = view.findViewById<Button>(R.id.buttonBack)

        prevBtn.setOnClickListener {
            val fragment = MainFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.mainlayout, fragment)?.commit()
        }

        db.collection("notes")
            .get()
            .addOnSuccessListener { result ->
                val notes = ArrayList<Note>()
                for (data in result.documents) {
                    val note = data.toObject(Note::class.java)
                    if (note != null) {
                        note.id = data.id
                        notes.add(note)
                    }
                }

                myAdapter = RecyclerViewAdapter(notes, this@NewInputFragment)
            }
            .addOnFailureListener{exception->
                Log.w("MainActivity","Error getting documents.",exception)
            }
        val inputBtn=view.findViewById<Button>(R.id.buttonInput)
        val nameET=view.findViewById<TextInputEditText>(R.id.editName)
        val addressET=view.findViewById<TextInputEditText>(R.id.editAddress)
        val guiltyRG=view.findViewById<RadioGroup>(R.id.radioGroupGuilty)
        val guiltyRB=view.findViewById<RadioButton>(R.id.radioButtonGuilty)
        val notGuiltyRB=view.findViewById<RadioButton>(R.id.radioButtonNotGuilty)
        var selectedText: String =""

        guiltyRG.setOnCheckedChangeListener { _, checkedId ->
            val radio: RadioButton = view.findViewById(checkedId)!!
            when (radio) {
                guiltyRB -> {
                    selectedText = radio.text.toString()
                }
                notGuiltyRB -> {
                    selectedText = radio.text.toString()
                }
            }
        }


            inputBtn.setOnClickListener {
                val note = Note("", nameET.text.toString(), addressET.text.toString(), selectedText)
            db.collection("notes").add(note).addOnSuccessListener {
                note.id = it.id
                myAdapter.addNote(note)
            }
            Toast.makeText(context, "Note placed", Toast.LENGTH_SHORT).show()
            nameET.setText("")
            addressET.setText("")
                guiltyRB.setChecked(false)
                notGuiltyRB.setChecked(false)
        }

        return view
    }
    override fun onNoteButtonClick(index: Int, note: Note, clickType: NoteClickType) {

    }
}