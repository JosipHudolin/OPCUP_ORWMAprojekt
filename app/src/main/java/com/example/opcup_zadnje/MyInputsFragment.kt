package com.example.opcup_zadnje

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MyInputsFragment : Fragment(),RecyclerViewAdapter.ContentListener {

    private val db = Firebase.firestore
    private lateinit var myAdapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_inputs, container, false)

        val backBtn=view.findViewById<Button>(R.id.buttonMyInputsBack)

        backBtn.setOnClickListener{
            val fragment = MainFragment()
            val transaction=fragmentManager?.beginTransaction()
            transaction?.replace(R.id.mainlayout, fragment)?.commit()
        }

        val recyclerView=view.findViewById<RecyclerView>(R.id.recyclerView)

        db.collection("notes")
            .get()
            .addOnSuccessListener { result->
                val notes=ArrayList<Note>()
                for(data in result.documents){
                    val note=data.toObject(Note::class.java)
                    if(note!=null){
                        note.id=data.id
                        notes.add(note)
                    }
                }

                myAdapter= RecyclerViewAdapter(notes,this@MyInputsFragment)
                recyclerView.apply {
                    layoutManager= LinearLayoutManager(activity)
                    adapter=myAdapter
                }

            }
            .addOnFailureListener{exception->
                Log.w("MainActivity","Error getting documents.",exception)
            }

        return view
    }

    override fun onNoteButtonClick(index: Int, note: Note, clickType: NoteClickType) {

        if(clickType==NoteClickType.REMOVE){
            myAdapter.removeNote(index)
            db.collection("notes")
                .document(note.id)
                .delete()
        }
    }
}