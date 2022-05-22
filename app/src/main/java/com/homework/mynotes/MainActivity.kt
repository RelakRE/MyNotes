package com.homework.mynotes

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), NotesListFragment.Listener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.main_frag, NotesMainList())
            .commit()
    }

    override fun itemClicked(id: Int) {
        val fragContainer = findViewById<FrameLayout>(R.id.fragment_container)
        val fragDetails = NotesFragment()
        fragDetails.setNoteId(id)
        if (fragContainer != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragDetails)
                .addToBackStack(null)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_frag, fragDetails)
                .addToBackStack(null)
                .commit()
        }
    }

}