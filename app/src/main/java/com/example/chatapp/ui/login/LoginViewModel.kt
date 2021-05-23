package com.example.chatapp.ui.login

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.dataBase.dao.UserDao
import com.example.chatapp.dataBase.model.User
import com.example.chatapp.ui.Data
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel: BaseViewModel<Navigator>() {
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val emailError = ObservableField<Boolean>()
    val passwordError = ObservableField<Boolean>()

    val firebaseAuth = Firebase.auth

    fun login(){
        showLoading.value = true
        if (!valid())return
        firebaseAuth.signInWithEmailAndPassword(email.get()?:"",password.get()?:"")
            .addOnCompleteListener { task ->
                showLoading.value = false
                if (task.isSuccessful){
                    getUserFromDB(firebaseAuth.currentUser?.uid)
                }else{
                    messageLiveData.value = task.exception?.localizedMessage
                }
            }
    }

    private fun getUserFromDB(userId: String?) {
        UserDao.getUser(userId?:"", OnCompleteListener {task->
            if (task.isSuccessful){
                val user = task.result?.toObject(User::class.java)
                Data.user = user
                navigator?.goToHome()
            }else{
                messageLiveData.value = task.exception?.localizedMessage
            }

        })
    }

    private fun valid(): Boolean {
        var isValid = true
        if (email.get().isNullOrBlank()){
            emailError.set(true)
            isValid =true
        }else{
            emailError.set(false)
        }
        if (password.get().isNullOrBlank()||password.get()?.length?:0<6){
            passwordError.set(true)
            isValid =true
        }else{
            passwordError.set(false)
        }
            return isValid
    }

    fun goToRegister(){
        navigator?.goToRegister()
    }
}