package com.homework.mynotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class NotesFragment : Fragment() {

    companion object {
        val NOTE_ID = "noteId"
    }

    private var noteId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            noteId = savedInstanceState.getInt(NOTE_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onStart() {
        super.onStart()
        view?.findViewById<TextView>(R.id.titleNotes)!!.text = NotesData.listNotes[noteId].title
        view?.findViewById<TextView>(R.id.detailNotes)!!.text =
            NotesData.listNotes[noteId].description
    }

    fun setNoteId(id: Int) {
        noteId = id
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(NOTE_ID, noteId)
    }

}