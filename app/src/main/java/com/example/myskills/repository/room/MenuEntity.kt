package com.example.myskills.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MenuEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int= 0,
    var name : String,
    var date : String,
    var size : Int,
    var description : String,
    var url : String,
    var category:String,
    var price : Int,
    var count :Int,
)
