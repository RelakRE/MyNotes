package com.homework.mynotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.fragment.app.Fragment

class NotesMainList : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        view?.findViewById<Button>(R.id.buttonAdd)?.setOnClickListener {
            val fragContainer = view?.findViewById<FrameLayout>(R.id.fragment_container)
//            if (fragContainer  == null){
//                val fragDetails = NotesFragment()
//                fragDetails.setNoteId(-1)
//                childFragmentManager.beginTransaction()
//                    .replace(R.id.main_frag, fragDetails)
//                    .addToBackStack(null)
//                    .commit()
//            }
        }
        return inflater.inflate(R.layout.fragment_list, container, false)
    }
}