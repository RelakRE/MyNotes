package com.homework.mynotes

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.homework.mynotes.Notes.NotesData
import com.homework.mynotes.Notes.NotesFragment
import com.homework.mynotes.Notes.NotesListMainFragment
import com.homework.mynotes.adapters.NoteRecyclerAdapter

class MainActivity : AppCompatActivity(), NoteRecyclerAdapter.Listener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initMainFragment()
    }

    override fun onBackPressed() {
        val currentFrag = supportFragmentManager.findFragmentById(R.id.main_frag) as Fragment
        if (currentFrag is NotesFragment) {
            if (currentFrag.view != null) {
                NotesData.replaceNote(
                    currentFrag.getIdNote(),
                    NotesData(currentFrag.getTitle(), currentFrag.getDescription())
                )
            }
        }
        super.onBackPressed()
    }

    override fun notesListItemClicked(id: Int) {
        val fragDetails = NotesFragment()
        fragDetails.setNoteId(id)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frag, fragDetails)
            .addToBackStack(null)
            .commit()
    }

    private fun initMainFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.main_frag, NotesListMainFragment())
            .commit()
    }

    fun test() {
        Snackbar.make(findViewById(R.id.main_frag), "Here's a Snackbar", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show()
    }

}