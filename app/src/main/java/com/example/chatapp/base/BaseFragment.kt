package com.example.chatapp.base

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer


abstract class BaseFragment <DB: ViewDataBinding,VM: BaseViewModel<*>> : Fragment() {

    lateinit var viewDataBinding: DB
    lateinit var viewModel: VM
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false)
        viewModel= initializeViewModel()
        viewModel.messageLiveData.observe(viewLifecycleOwner, Observer { message->
            showDialoge(message= message, posActionName = "OK",
                posAction = DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
        })
        viewModel.showLoading.observe(viewLifecycleOwner, Observer { show->
            if (show)
                showProgressDialoge("Loading.......")
            else
                hideProgressDialoge()
        })
        return viewDataBinding.root
    }
    abstract fun getLayoutId():Int
    abstract fun initializeViewModel():VM

    fun makeToast(message:String){
        Toast.makeText(context,"Hello user", Toast.LENGTH_LONG).show()
    }
    fun makeToast(messageId:Int){
        Toast.makeText(context,messageId, Toast.LENGTH_LONG).show()
    }

        var dialoge: ProgressDialog?=null
    fun showProgressDialoge(message:String){
        dialoge =   ProgressDialog(context)
        dialoge?.setCancelable(false)
        dialoge?.setMessage(message)
        dialoge?.show()
    }

    fun hideProgressDialoge(){
        dialoge?.dismiss()
    }

    // snack bar
    // dialoges
    fun showDialoge(title:String?=null,
                    message:String,
                    posActionName:String?=null,
                    posAction: DialogInterface.OnClickListener?=null,
                    negActionName:String?=null,
                    negAction: DialogInterface.OnClickListener?=null,
                    isCancelable: Boolean = true
                    ){
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)
        builder.setTitle(title)
        builder.setPositiveButton(posActionName,posAction)
        builder.setNegativeButton(negActionName,negAction)
        builder.setCancelable(isCancelable)
        builder.show()
    }
    fun showDialoge(titleId:Int?=null,
                    messageId:Int,
                    posActionName:Int?=null,
                    posAction: DialogInterface.OnClickListener?=null,
                    negActionName:Int?=null,
                    negAction: DialogInterface.OnClickListener?=null,
                    isCancelable:Boolean=true){
        val builder = AlertDialog.Builder(context)
        builder.setMessage(messageId)
        builder.setCancelable(isCancelable)
        if(titleId!=null)
            builder.setTitle(titleId)
        if (posActionName!=null)
            builder.setPositiveButton(posActionName,posAction)
        if (negActionName!=null)
            builder.setNegativeButton(negActionName,negAction)

        builder.show()
    }

}