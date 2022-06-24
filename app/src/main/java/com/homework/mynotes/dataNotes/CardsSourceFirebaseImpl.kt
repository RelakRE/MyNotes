package com.homework.mynotes.dataNotes

import android.util.Log
import androidx.annotation.NonNull
import com.google.firebase.firestore.FirebaseFirestore
import com.homework.mynotes.interfases.CardsSource
import java.util.ArrayList
import kotlin.collections.ArrayList


class CardsSourceFirebaseImpl : CardsSource {

    private val CARDS_COLLECTION = "Notes"
    private val TAG = "[CardsSourceFirebaseImpl]"

    private val store = FirebaseFirestore.getInstance()

    private val collection = store.collection(CARDS_COLLECTION)

    val cardsData: List<CardData> = ArrayList()

    fun init(cardsSourceResponse: CardsSourceResponse) {
        collection.get()
            .addOnCompleteListener { it ->
                if (it.isSuccessful) {
                    it.result.forEach {
                        val doc = it.data
                        val id = it.id
                        val cardData = CardDataMapping.toCardData(id, doc)
                        cardsData.add(cardData)
                    }

                    Log.d(TAG, "success " + cardsData.size() + " qnt");
                    cardsSourceResponse.initialized(CardsSourceFirebaseImpl.this);
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
    })
    .addOnFailureListener(new OnFailureListener()
    {
        @Override
        public void onFailure(@NonNull Exception e) {
            Log.d(TAG, "get failed with ", e);
        }
    });
    return this;
}


override fun getCardData(position: Int): CardData {
    TODO("Not yet implemented")
}

override fun size(): Int {
    TODO("Not yet implemented")
}

override fun deleteCardData(position: Int) {
    TODO("Not yet implemented")
}

override fun updateCardData(position: Int, cardData: CardData?) {
    TODO("Not yet implemented")
}

override fun addCardData(cardData: CardData?) {
    TODO("Not yet implemented")
}

override fun clearCardData() {
    TODO("Not yet implemented")
}
}