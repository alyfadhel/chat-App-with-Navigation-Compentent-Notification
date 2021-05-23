package com.example.chatapp.dataBase.model

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat

data class Message (var id: String?=null,
                    var messageText: String?=null,
                    var senderName: String?=null,
                    var senderId: String?=null,
                    var roomId: String?=null,
                    var time: Timestamp?=null){
    fun formatTime(): String{
        val date = time?.toDate()
        val simpleDateFormat = SimpleDateFormat("hh:mm:ss dd MMM")
        return simpleDateFormat.format(date)


    }
}