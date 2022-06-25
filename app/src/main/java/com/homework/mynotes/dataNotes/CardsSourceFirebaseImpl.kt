package com.homework.mynotes.dataNotes

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.homework.mynotes.interfases.CardsSource


class CardsSourceFirebaseImpl : CardsSource {

    private val CARDS_COLLECTION = "Notes"
    private val TAG = "[CardsSourceFirebaseImpl]"

    private val store = FirebaseFirestore.getInstance()

    private val collection = store.collection(CARDS_COLLECTION)

    private val cardsData: List<CardData> = ArrayList()

    fun init(cardsSourceResponse: CardsSourceResponse) {
        collection.get()
            .addOnCompleteListener { it ->
                if (it.isSuccessful) {
                    it.result.forEach {
                        val doc = it.data
                        val id = it.id
                        val cardData = CardDataMapping.toCardData(id, doc)
                        (cardsData as ArrayList<CardData>).add(cardData)
                    }

                    Log.d(TAG, "success " + cardsData.size + " qnt");
                    cardsSourceResponse.initialized(CardsSourceFirebaseImpl.this);
                } else {
                    Log.d(TAG, "get failed with ", it.exception);
                }
            }
    }


    override fun getCardData(position: Int): CardData {
        return cardsData[position]
    }

    override fun size(): Int {
        return cardsData.size
    }

    override fun deleteCardData(position: Int) {
        collection.
        cardsData.drop(position)
    }

    override fun updateCardData(position: Int, cardData: CardData) {
        (cardsData as ArrayList<CardData>).add(position, cardData)
    }

    override fun addCardData(cardData: CardData) {
        collection.add(CardDataMapping.toDocument(cardData))
        (cardsData as ArrayList<CardData>).add(cardData)
    }

    override fun clearCardData() {

    }
}