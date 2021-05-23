package com.example.chatapp.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.base.BaseFragment
import com.example.chatapp.databinding.ActivityRegisterBinding
import com.example.chatapp.ui.home.HomeActivity

class RegisterFragment: BaseFragment<ActivityRegisterBinding,RegisterViewModel>(),Navigator {
    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initializeViewModel(): RegisterViewModel {
        return ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.vm = viewModel
        viewModel.navigator = this
    }

    override fun goToHome() {
        findNavController().navigate(R.id.action_registerFragment_to_homeFragment2)
//        val intent = Intent(this, HomeActivity::class.java)
//        startActivity(intent)
    }
}