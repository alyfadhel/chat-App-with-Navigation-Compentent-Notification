package com.example.chatapp.ui.roomDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.dataBase.model.Message
import com.example.chatapp.databinding.LayoutRecivedBinding
import com.example.chatapp.databinding.LayoutSentBinding
import com.example.chatapp.ui.Data

class MessageAdapter(var messageList: MutableList<Message>): RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {
    val SENDER_MESSAGES = 0
    val RECEIVED_MESSAGES = 1
    override fun getItemViewType(position: Int): Int {
        val message = messageList[position]
        if (message.senderId.equals(Data.user?.id)){
            return SENDER_MESSAGES
        }else{
            return RECEIVED_MESSAGES
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
            if (viewType==SENDER_MESSAGES){
                val itemBinding: LayoutSentBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.layout_sent,parent,false)

        return SentMessageViewHolder(itemBinding)
            }

                val itemBinding: LayoutRecivedBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                        R.layout.layout_recived,parent,false)

        return ReceivedMessageViewHolder(itemBinding)


    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messageList[position]
        holder.bind(message)

    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    fun addMessages(addMessages: MutableList<Message>) {
        val oldMessage = messageList.size
        messageList.addAll(addMessages)
        notifyItemRangeInserted(oldMessage,addMessages.size)
    }

    abstract class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
             abstract fun bind(message: Message)
    }


    class SentMessageViewHolder(val itemBinding: LayoutSentBinding):
            MessageViewHolder(itemBinding.root){
        override fun bind(message: Message) {
            itemBinding.setMessage(message)
            itemBinding.invalidateAll()
        }
    }

    class ReceivedMessageViewHolder(val itemBinding: LayoutRecivedBinding):
            MessageViewHolder(itemBinding.root) {
        override fun bind(message: Message) {
            itemBinding.setMessage(message)
            itemBinding.invalidateAll()
        }
    }
}