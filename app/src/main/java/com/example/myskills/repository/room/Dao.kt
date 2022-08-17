package com.example.myskills.repository.room

import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao { @Insert(onConflict = OnConflictStrategy.REPLACE)
fun createMenu(mutableList:MutableList<MenuEntity>)

    @Query("SELECT * FROM MenuEntity")
    fun getAllmenudata(): MutableList<MenuEntity>

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
    fun createBuscetdata(cartEntity: CartEntity)

    @Query("SELECT * FROM CartEntity")
    fun getBuscetdata(): MutableList<CartEntity>

    @Query("SELECT * FROM CartEntity WHERE id LIKE :id")
    fun getBuscetdatabyid(id: Int): CartEntity


    @Query("DELETE FROM CartEntity WHERE id LIKE :id")
    fun deleteBuscet(id: Int)


    @Query("UPDATE CartEntity SET count =:count WHERE id LIKE :id")
    fun updateBuscetcount(id: Int,count : Int)

    @Query("UPDATE CartEntity SET state =:state WHERE id LIKE :id")
    fun updateBuscetstate(id: Int,state : Boolean)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createsentorder(sentorderEntity: SentorderEntity)

    @Query("SELECT * FROM SentorderEntity")
    fun getSentosrderdata(): MutableList<SentorderEntity>


    @Query("DELETE FROM SentorderEntity WHERE id LIKE :id")
    fun deletesentorder(id: Int)




}