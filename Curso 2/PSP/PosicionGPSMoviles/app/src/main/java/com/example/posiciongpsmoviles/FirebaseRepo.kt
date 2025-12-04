package com.example.posiciongpsmoviles

import com.google.firebase.firestore.FirebaseFirestore

class FirebaseRepo {

    private val db = FirebaseFirestore.getInstance()

    fun sendLocation(location: UserLocation) {
        db.collection("locations")
            .document(location.userId)
            .set(location)
    }
}
