package com.example.myskills

import com.example.myskills.repository.room.CartEntity
import com.example.myskills.repository.room.MenuEntity
import com.example.myskills.repository.room.OrderEntity
import com.example.myskills.repository.room.SentorderEntity

interface Repository {
    fun createMenu()
    fun getMenue():MutableList<MenuEntity>
    fun createOrder()
    fun getOrderData(): OrderEntity
    fun updateOrder(orderEntity: OrderEntity)
    fun getOrdertotalcount():Int
    fun getOrdertotalprice():Int
    fun updateOrderCountandPrice(id: Int,totalcount: Int,totalprice: Int)
    fun getProductbyid(id : Int): MenuEntity
    fun isfirstTime():Boolean{
        return false
    }
    fun creatBuscet(cartEntity: CartEntity)
    fun getBuscet():MutableList<CartEntity>
    fun getBuscetdatabyid(id: Int): CartEntity
    fun deleteBusket(id: Int)
    fun updateBusketcount(id: Int, count : Int)
    fun updateBuscetstate(id: Int,state : Boolean)
    fun createsentorder(sentorderEntity: SentorderEntity)
    fun deletesentorder(id: Int)
    fun getSentosrderdata(): MutableList<SentorderEntity>

}