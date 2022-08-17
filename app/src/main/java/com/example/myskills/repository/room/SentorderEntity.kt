package com.example.myskills.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class SentorderEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var date : String,
    var status : Boolean,
    var adres : String,
    var phone_number:String,
    var totalcount: Int,
    var totalprice: Int
)
