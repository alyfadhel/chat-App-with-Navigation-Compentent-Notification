package com.example.chatapp.dataBase.model

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

object DataBase {
    private var dataBase = FirebaseFirestore.getInstance()

    const val USERS_PATH = "users"
    fun getUsersCollection():CollectionReference{
        return dataBase.collection(USERS_PATH)
    }

    const val ROOMS_PATH = "rooms"
    fun getRoomsCollection():CollectionReference{
        return dataBase.collection(ROOMS_PATH)
    }


}