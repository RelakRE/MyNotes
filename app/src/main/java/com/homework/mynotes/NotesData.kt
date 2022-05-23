package com.homework.mynotes

import java.util.*

class NotesData(var title: String, var description: String, var dateOFCreation: Calendar) {

    companion object {
        private val currentTime = Calendar.getInstance()
        val listNotes = arrayListOf<NotesData>(
            NotesData("Список покупок", "Молоко\nКорову", currentTime),
            NotesData("Выбросить мусор", "", currentTime)
        )
    }

    fun add(newNotes: NotesData){
        listNotes.add(newNotes)
    }
}