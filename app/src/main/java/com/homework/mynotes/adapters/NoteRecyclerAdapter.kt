package com.homework.mynotes.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.homework.mynotes.MainActivity
import com.homework.mynotes.Notes.NotesData
import com.homework.mynotes.R
import java.text.DateFormat


class NoteRecyclerAdapter(val context: Context) :
    RecyclerView.Adapter<NoteRecyclerAdapter.Holder>() {

    internal interface Listener {
        fun notesListItemClicked(id: Int)
    }

    private var mRecentlyDeletedItem: NotesData? = null
    private var mRecentlyDeletedItemPosition: Int? = null

    class Holder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {
        private val titleView: TextView = itemView.findViewById(com.homework.mynotes.R.id.title)
        private val descriptionView: TextView = itemView.findViewById(com.homework.mynotes.R.id.description)
        private val dateView: TextView = itemView.findViewById(com.homework.mynotes.R.id.date)

        private val listener: Listener? = context as Listener

        fun bind(position: Int) {
            val notesData = NotesData.findNoteById(position)
            titleView.text = getTextItem(notesData.title)
            descriptionView.text = getTextItem(notesData.description)
            dateView.text = DateFormat.getDateInstance().format(notesData.dateOFCreation.time)
        }

        fun setListener(position: Int) {
            itemView.setOnClickListener {
                listener?.notesListItemClicked(position)
            }
        }

        private fun getTextItem(fullDescription: String): String {
            val subString = fullDescription.substringBefore("\n")
            return if (subString.length > 20) {
                subString.substring(0, 20)
            } else {
                subString
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(
                com.homework.mynotes.R.layout.note_list_item,
                parent, false
            ), context
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(position)
        holder.setListener(position)
    }

    override fun getItemCount(): Int {
        return NotesData.getSize()
    }

    fun deleteItem(adapterPosition: Int) {
        mRecentlyDeletedItem = NotesData.findNoteById(adapterPosition)
        mRecentlyDeletedItemPosition = adapterPosition
        NotesData.remove(adapterPosition)
        notifyItemRemoved(adapterPosition)
        showUndoSnackbar()
    }

    private fun showUndoSnackbar() {
        if (context is MainActivity){
            val view: View = context.findViewById(R.id.main_frag)

            val snackbar: Snackbar = Snackbar.make(
                view, R.string.snack_bar_text,
                Snackbar.LENGTH_LONG
            )
            snackbar.setAction(R.string.snack_bar_undo) { undoDelete() }
            snackbar.show()
        }

    }

    private fun undoDelete() {
        NotesData.addNote(
            mRecentlyDeletedItem!!
        )
//        notifyItemInserted(mRecentlyDeletedItemPosition!!)
        notifyItemInserted(NotesData.getSize()-1)
    }


}