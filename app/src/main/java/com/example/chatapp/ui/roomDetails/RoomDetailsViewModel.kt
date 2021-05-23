package com.example.chatapp.ui.roomDetails

import androidx.databinding.ObservableField
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.dataBase.dao.MessagesDao
import com.example.chatapp.dataBase.model.Message
import com.example.chatapp.ui.Data
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.Timestamp

class RoomDetailsViewModel: BaseViewModel<Navigator>() {

    val messageField = ObservableField<String>()
    var roomId: String?=null
    fun sendMessage(){
        if (!valid())return
        val messageObj = Message(messageText = messageField.get(),
                                senderId = Data.user?.id,
                                senderName = Data.user?.userName,
                                roomId = roomId ,
                                time = Timestamp.now()
                )
        MessagesDao.sendMessage(messageObj, OnSuccessListener { task->
            messageField.set("")
        },
        OnFailureListener { fail->
            messageLiveData.value = fail.localizedMessage
        }
            )
    }

    private fun valid(): Boolean {
        if (messageField.get().isNullOrBlank()){
            return false
        }
        return true
    }
}