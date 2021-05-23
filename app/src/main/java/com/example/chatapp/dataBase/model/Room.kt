package com.example.chatapp.dataBase.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room (var id: String?=null,
                 var name: String?=null,
                 var desc: String?=null): Parcelable
