package com.example.chatapp.ui.roomDetails

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.R
import com.example.chatapp.base.BaseFragment
import com.example.chatapp.dataBase.dao.MessagesDao
import com.example.chatapp.dataBase.model.Message
import com.example.chatapp.dataBase.model.Room
import com.example.chatapp.databinding.ActivityRoomDetailsBinding
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.Query

class RoomDetailsFragment: BaseFragment<ActivityRoomDetailsBinding,RoomDetailsViewModel>(),Navigator {
    var room: Room?=null
    val args: RoomDetailsFragmentArgs by navArgs()
    lateinit var messageAdapter: MessageAdapter
    override fun getLayoutId(): Int {
        return R.layout.activity_room_details
    }

    override fun initializeViewModel(): RoomDetailsViewModel {
        return ViewModelProvider(this).get(RoomDetailsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  room = intent.getParcelableExtra("room")
        room = args.room
        viewModel.roomId = room?.id
        viewDataBinding.vm = viewModel
        setupViews()
        subscribeToRoomMessage(room?.id)
    }

    private fun subscribeToRoomMessage(roomId: String?) {
        MessagesDao.getMessageRef(roomId?:"")
            .orderBy("time", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    showDialoge(message = e.localizedMessage)
                    return@addSnapshotListener
                }
                val addMessages = mutableListOf<Message>()
                for (dc in snapshots!!.documentChanges) {
                    when (dc.type) {
                        DocumentChange.Type.ADDED -> {
                            val messages = dc.document.toObject(Message::class.java)
                            addMessages.add(messages)
                        }
                        //                            Log.d(TAG, "New city: ${dc.document.data}")
//                            DocumentChange.Type.MODIFIED -> Log.d(TAG, "Modified city: ${dc.document.data}")
                    }
                }
                messageAdapter.addMessages(addMessages)
                if (addMessages.size > 0) viewDataBinding.sendRecyclerView.smoothScrollToPosition(messageAdapter.messageList.size - 1)
            }
    }

    private fun setupViews() {
        messageAdapter = MessageAdapter(mutableListOf())
        val layoutManager =  LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        layoutManager.stackFromEnd = false
        viewDataBinding.sendRecyclerView.layoutManager = layoutManager

        viewDataBinding.sendRecyclerView.adapter = messageAdapter
    }
}