package com.example.chatapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.base.BaseFragment
import com.example.chatapp.dataBase.model.Room
import com.example.chatapp.databinding.ActivityHomeBinding
import com.example.chatapp.ui.addRoom.AddRoomActivity
import com.example.chatapp.ui.roomDetails.RoomDetailsActivity
import com.example.chatapp.ui.roomDetails.RoomDetailsFragment
import com.example.chatapp.ui.roomDetails.RoomDetailsFragmentArgs

class HomeFragment: BaseFragment<ActivityHomeBinding,HomeViewModel>(),Navigator {
    lateinit var roomAdapter: RoomAdapter
    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun initializeViewModel(): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.vm = viewModel
        viewModel.navigator = this
        setupViews()
        observeLiveData()
    }



    private fun observeLiveData() {
        viewModel.roomsLiveData.observe(viewLifecycleOwner, Observer {
            roomAdapter.changeData(it)
        })
    }


    override fun onStart() {
        super.onStart()
        viewModel.getRoomsList()
    }

    private fun setupViews() {
        roomAdapter = RoomAdapter(listOf())
        viewDataBinding.RecyclerView.adapter = roomAdapter
        roomAdapter.onItemClickListener = object : RoomAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, room: Room) {
//                val intent = Intent(this@HomeActivity, RoomDetailsActivity::class.java)
//                intent.putExtra("room",room)
//                startActivity(intent)
                val action = HomeFragmentDirections.actionHomeFragmentToRoomDetailsFragment(room)
                findNavController().navigate(action)
            }
        }
    }

    override fun goToOpenListRoom() {
      findNavController().navigate(R.id.action_homeFragment_to_addRoomFragment)
//        val intent = Intent(this, AddRoomActivity::class.java)
//        startActivity(intent)
    }
}