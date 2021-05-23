package com.example.chatapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.base.BaseFragment
import com.example.chatapp.databinding.ActivityLoginBinding
import com.example.chatapp.ui.home.HomeActivity
import com.example.chatapp.ui.register.RegisterActivity

class LoginFragment: BaseFragment<ActivityLoginBinding,LoginViewModel>(),Navigator {
    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initializeViewModel(): LoginViewModel {
        return ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.vm = viewModel
        viewModel.navigator = this
    }

    override fun goToRegister() {
//        val intent = Intent(this, RegisterActivity::class.java)
//        startActivity(intent)
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)

    }

    override fun goToHome() {
//        val intent = Intent(this, HomeActivity::class.java)
//        startActivity(intent)
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }
}