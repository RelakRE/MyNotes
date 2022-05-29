package com.homework.mynotes.Notes

import android.os.Bundle
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.homework.mynotes.R
import com.homework.mynotes.SwipeToDeleteCallback
import com.homework.mynotes.adapters.NoteRecyclerAdapter

class NotesListMainFragment : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<NoteRecyclerAdapter.Holder>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        val recyclerAdapter = NoteRecyclerAdapter(layoutInflater.context)
        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(recyclerAdapter))

        itemTouchHelper.attachToRecyclerView(recyclerView)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = recyclerAdapter
        }

        intiFABAdd()
    }


    private fun intiFABAdd() {
        view?.findViewById<View>(R.id.fab_add)?.setOnClickListener {
            openNewNotes()
        }
    }

    private fun openNewNotes() {
        val newFragDetail = NotesFragment()
        newFragDetail.setNoteId(NotesFragment.getNewID())

        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.main_frag, newFragDetail)
            ?.addToBackStack(null)
            ?.commit()
    }

}