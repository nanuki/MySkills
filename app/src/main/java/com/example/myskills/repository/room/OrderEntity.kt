package com.example.myskills.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int= 1,
    var date : String,
    var status : Boolean,
    var address : String,
    var phone_number:String,
    var totalcount: Int,
    var totalprice: Int,

)
