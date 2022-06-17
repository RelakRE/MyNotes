package com.homework.mynotes.Notes

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.homework.mynotes.MainActivity
import com.homework.mynotes.R
import com.homework.mynotes.SwipeToDeleteCallback
import com.homework.mynotes.adapters.NoteRecyclerAdapter

class NotesListMainFragment : Fragment() {

    private lateinit var recyclerAdapter : NoteRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerAdapter = NoteRecyclerAdapter(layoutInflater.context)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
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
            val curActivity = requireActivity()
            if (curActivity is MainActivity) {
                curActivity.openNewNotes()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_context_notes, menu)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.menu_context_notes, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_context_delete -> {
                recyclerAdapter.deleteItem(recyclerAdapter.menuPosition)
                return true
            }
            R.id.menu_context_edit -> {
                recyclerAdapter.editNote(recyclerAdapter.menuPosition)
                return true
            }
        }
        return super.onContextItemSelected(item)
    }
}