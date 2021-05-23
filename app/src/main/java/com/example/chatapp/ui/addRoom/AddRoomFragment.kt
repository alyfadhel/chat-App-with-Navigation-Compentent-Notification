package com.example.chatapp.ui.addRoom

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.system.Os.remove
import android.text.TextUtils.replace
import android.view.Menu
import android.view.View
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.core.app.ActivityCompat.finishAfterTransition
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.base.BaseFragment
import com.example.chatapp.databinding.ActivityAddRoomBinding
import com.example.chatapp.ui.home.HomeFragment

class AddRoomFragment: BaseFragment<ActivityAddRoomBinding,AddRoomViewModel>(),Navigator {
    override fun getLayoutId(): Int {
        return R.layout.activity_add_room
    }

    override fun initializeViewModel(): AddRoomViewModel {
        return ViewModelProvider(this).get(AddRoomViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.vm = viewModel
        viewModel.navigator = this
        viewModel.roomAdded.observe(viewLifecycleOwner, Observer {
            showDialoge(message = "Room Added", posActionName = "OK",
                posAction = DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                    activity?.onBackPressed()
                },
                isCancelable = false
            )
        })

    }
}