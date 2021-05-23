package com.example.chatapp.dataBase.dao

import com.example.chatapp.dataBase.model.DataBase
import com.example.chatapp.dataBase.model.Room
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.QuerySnapshot

class RoomsDao {
    companion object{
        fun insertRoom(room: Room, onCompleteListener: OnCompleteListener<Void>){
           val document =  DataBase.getRoomsCollection().document()
            room.id = document.id
            document.set(room)
                .addOnCompleteListener(onCompleteListener)
        }
        fun getRoomsList(onSuccessListener: OnSuccessListener<QuerySnapshot>,onFailureListener: OnFailureListener){
            DataBase.getRoomsCollection()
                .get().addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener)
        }
    }
}