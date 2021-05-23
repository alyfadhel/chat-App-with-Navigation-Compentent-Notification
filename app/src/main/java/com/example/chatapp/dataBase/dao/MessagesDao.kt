package com.example.chatapp.dataBase.dao

import android.os.Message
import com.example.chatapp.dataBase.model.DataBase
import com.example.chatapp.dataBase.model.Room
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.CollectionReference

class MessagesDao {
    companion object{
        fun sendMessage(message: com.example.chatapp.dataBase.model.Message,
        onSuccessListener: OnSuccessListener<Void>,onFailureListener: OnFailureListener){
            val room = DataBase.getRoomsCollection().document(message.roomId?:"")
            val messages = room.collection("messages")
            val newMessageDoc = messages.document()
            message.id = newMessageDoc.id
            newMessageDoc.set(message)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener)
        }
        fun getMessageRef(roomId: String): CollectionReference{
            return DataBase.getRoomsCollection().document(roomId)
                .collection("messages")
        }
    }
}