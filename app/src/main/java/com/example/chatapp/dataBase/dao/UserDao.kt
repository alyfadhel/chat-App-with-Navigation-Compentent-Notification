package com.example.chatapp.dataBase.dao

import com.example.chatapp.dataBase.model.DataBase
import com.example.chatapp.dataBase.model.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.DocumentSnapshot

class UserDao {
    companion object{
        fun addUser(user: User, onCompleteListener: OnCompleteListener<Void>){
            DataBase.getUsersCollection().document(user.id?:"")
                .set(user).addOnCompleteListener(onCompleteListener)
        }
        fun getUser(userId: String, onCompleteListener: OnCompleteListener<DocumentSnapshot>){
            DataBase.getUsersCollection().document(userId)
                .get().addOnCompleteListener(onCompleteListener)
        }

    }
}