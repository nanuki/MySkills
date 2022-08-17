package com.example.myskills.repository

import com.example.myskills.repository.room.CartEntity
import com.example.myskills.repository.room.MenuEntity
import com.example.myskills.repository.room.OrderEntity
import com.example.myskills.repository.room.SentorderEntity

interface Repository {
    fun createMenu()
    fun getMenu():MutableList<MenuEntity>
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
    fun createCart(cartEntity: CartEntity)
    fun getCart():MutableList<CartEntity>
    fun getCartdatabyid(id: Int): CartEntity
    fun deleteCart(id: Int)
    fun updateCartcount(id: Int, count : Int)
    fun updateCartstate(id: Int, state : Boolean)
    fun createSentorder(sentorderEntity: SentorderEntity)
    fun deleteSentorder(id: Int)
    fun getSentosrderdata(): MutableList<SentorderEntity>

}