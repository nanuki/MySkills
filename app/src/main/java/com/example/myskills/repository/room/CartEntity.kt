package com.example.myskills.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String,
    var date: String,
    var description: String,
    var url: String,
    var category:String,
    var price: Int,
    var count:Int,
    var productid: Int,
    var senderEntity: Int,
    var state : Boolean
)
