package com.homework.mynotes.dataNotes

import com.google.firebase.Timestamp

class CardDataMapping {

    companion object Fields{
        const val DATE = "dateOFCreation"
        const val TITLE = "title"
        const val DESCRIPTION = "description"

        fun toCardData(id: String, doc: Map<String, Any>): CardData {
            val timeStamp: Timestamp? = doc[DATE] as Timestamp?
            val answer = CardData(
                doc[TITLE] as String,
                doc[DESCRIPTION] as String
            )
            if (id.isNotEmpty()) {
                answer.id = id
            }
            return answer
        }

        fun toDocument(cardData: CardData): Map<String, Any> {
            val answer: MutableMap<String, Any> = HashMap()
            answer[TITLE] = cardData.title
            answer[DESCRIPTION] = cardData.description
//        answer[DATE] = cardData.getDate()
            return answer
        }

    }


}