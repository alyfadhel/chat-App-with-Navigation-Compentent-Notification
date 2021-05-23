package com.example.chatapp.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.dataBase.dao.RoomsDao
import com.example.chatapp.dataBase.model.Room
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener

class HomeViewModel: BaseViewModel<Navigator>() {

    val roomsLiveData = MutableLiveData<List<Room>>()
    fun getRoomsList(){
        RoomsDao.getRoomsList(OnSuccessListener { result->
            val roomsList: MutableList<Room> = mutableListOf()
            for (document in result){
                val room = document.toObject(Room::class.java)
                roomsList.add(room)
            }
            roomsLiveData.value = roomsList
        },

            OnFailureListener { task->
                messageLiveData.value = task.localizedMessage
            })
    }
    fun addListRoom(){
        navigator?.goToOpenListRoom()
    }
}