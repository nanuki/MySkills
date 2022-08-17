package com.example.myskills.repository.room

import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao { @Insert(onConflict = OnConflictStrategy.REPLACE)
fun createMenu(mutableList:MutableList<MenuEntity>)

    @Query("SELECT * FROM MenuEntity")
    fun getMenudata(): MutableList<MenuEntity>
    @Query("SELECT * FROM MenuEntity WHERE id LIKE:id LIMIT 1")
    fun getProductbyid(id : Int): MenuEntity
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createOrder(orderEntity:OrderEntity)

    @Update
    fun updateOrder(orderEntity: OrderEntity)
    @Query("UPDATE OrderEntity SET totalcount =:totalcount,totalprice = :totalprice WHERE id LIKE :id")
    fun updateOrderCountandPrice(id: Int,totalcount: Int, totalprice: Int)
    @Query("SELECT * FROM OrderEntity")
    fun getOrderData(): OrderEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createCartdata(cartEntity: CartEntity)
    @Query("SELECT * FROM CartEntity")
    fun getCartdata(): MutableList<CartEntity>
    @Query("SELECT * FROM CartEntity WHERE id LIKE :id")
    fun getCartdatabyid(id: Int): CartEntity
    @Query("DELETE FROM CartEntity WHERE id LIKE :id")
    fun deleteCart(id: Int)
    @Query("UPDATE CartEntity SET count =:count WHERE id LIKE :id")
    fun updateCartcount(id: Int, count : Int)
    @Query("UPDATE CartEntity SET state =:state WHERE id LIKE :id")
    fun updateCartstate(id: Int, state : Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createSentorder(sentorderEntity: SentorderEntity)
    @Query("SELECT * FROM SentorderEntity")
    fun getSentorderdata(): MutableList<SentorderEntity>
    @Query("DELETE FROM SentorderEntity WHERE id LIKE :id")
    fun deleteSentorder(id: Int)


}