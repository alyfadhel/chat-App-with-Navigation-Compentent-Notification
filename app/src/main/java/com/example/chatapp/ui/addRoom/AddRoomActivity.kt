package com.example.chatapp.ui.addRoom

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.base.BaseActivity
import com.example.chatapp.databinding.ActivityAddRoomBinding

class AddRoomActivity : BaseActivity<ActivityAddRoomBinding,AddRoomViewModel>(),Navigator {
    override fun getLayoutId(): Int {
        return R.layout.activity_add_room
    }

    override fun initializeViewModel(): AddRoomViewModel {
        return ViewModelProvider(this).get(AddRoomViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
        viewModel.navigator = this
        viewModel.roomAdded.observe(this, Observer {
            showDialoge(message = "Room Added", posActionName = "OK",
            posAction = DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
                finish()
            },
                isCancelable = false
                )
        })
    }
}