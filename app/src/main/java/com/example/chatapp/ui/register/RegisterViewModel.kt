package com.example.chatapp.ui.register

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.dataBase.dao.UserDao
import com.example.chatapp.dataBase.model.User
import com.example.chatapp.ui.Data
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterViewModel: BaseViewModel<Navigator>() {
    val name = ObservableField<String>()
    val email = ObservableField<String>()
    val userName = ObservableField<String>()
    val password = ObservableField<String>()
    val nameError = ObservableField<Boolean>()
    val emailError = ObservableField<Boolean>()
    val userNameError = ObservableField<Boolean>()
    val passwordError = ObservableField<Boolean>()

    val firebaseAuth = Firebase.auth

    fun register() {
        showLoading.value = true
        if (!valid()) return
        firebaseAuth.createUserWithEmailAndPassword(email.get()?:"",password.get()?:"")
            .addOnCompleteListener { task ->
                showLoading.value = false
                if (task.isSuccessful){
                    val firebaseUser = firebaseAuth.currentUser
                    val user = User(firebaseUser?.uid,name.get(),email.get(),userName.get())
                    addUsersInDB(user)
                }else{
                    messageLiveData.value = task.exception?.localizedMessage
                }
            }
    }

    private fun addUsersInDB(user: User) {
        UserDao.addUser(user, OnCompleteListener { task ->
            if (task.isSuccessful){
                navigator?.goToHome()
                Data.user = user
            }else{
                messageLiveData.value = task.exception?.localizedMessage
            }
        })
    }

    private fun valid(): Boolean {

        var isValid = true
        if (name.get().isNullOrBlank()){
            nameError.set(true)
            isValid = false
        }else{
            nameError.set(false)
        }
        if (email.get().isNullOrBlank()){
            emailError.set(true)
            isValid = false
        }else{
            emailError.set(false)
        }
        if (userName.get().isNullOrBlank()){
            userNameError.set(true)
            isValid = false
        }else{
            userNameError.set(false)
        }
        if (password.get().isNullOrBlank()||password.get()?.length?:0<6){
            passwordError.set(true)
            isValid = false
        }else{
            passwordError.set(false)
        }
        return isValid
    }

}