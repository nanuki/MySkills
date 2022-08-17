package com.example.myskills.repository.room

import android.content.Context
import com.example.myskills.R
import com.example.myskills.repository.Repository
import com.example.myskills.repository.SharedPrefRepo

class RoomRepository(val context: Context): Repository {
    private var sessionManager = SharedPrefRepo(context)
    override fun createMenu() {
        sessionManager.saveAuthToken("aaa")
        val pizza1 = MenuEntity(0,"Veggie pizza","",1, context.getString(R.string.veggie_pizza),"","pizza",10,1)
        val pizza2 = MenuEntity(0,"Chicken pizza","",1,  context.getString(R.string.Chicken_pizza),"","pizza",11,1)
        val pizza3 = MenuEntity(0,"Assorted","",1, context.getString(R.string.assorted),"","pizza",15,1)
        val pizza4 = MenuEntity(0,"Chicken tikka","",1, context.getString(R.string.chicken_tika),"","pizza",12,1)
        val pizza5 = MenuEntity(0,"American hot","",1, context.getString(R.string.american_hot),"","pizza",13,1)
        val pizza6 = MenuEntity(0,"Veggie pizza","",1, context.getString(R.string.Veggie_overlord),"","pizza",11,1)

        val burger1 = MenuEntity(0,"Beef Burger","",1, context.getString(R.string.burger_1),"","burger",8,1)
        val burger2 = MenuEntity(0,"Beef Lava Burger","",1, context.getString(R.string.burger_2),"","burger",7,1)
        val burger3 = MenuEntity(0,"Beef Eggy Burger","",1, context.getString(R.string.burger_3),"","burger",6,1)
        val burger4 = MenuEntity(0,"Chicken Burger","",1, context.getString(R.string.burger_4),"","burger",5,1)
        val burger5 = MenuEntity(0,"Veggie Burger","",1, context.getString(R.string.burger_5),"","burger",4,1)
        val burger6 = MenuEntity(0,"Veggie Boom Burger","",1, context.getString(R.string.burger_6),"","burger",5,1)

        val juice1 = MenuEntity(0,"Coca-Cola","",1, "250ml","","juice",1,1)
        val juice2 = MenuEntity(0,"Fanta","",1, "250ml","","juice",1,1)
        val juice3 = MenuEntity(0,"Sprite","",1, "250ml","","juice",1,0)
        val juice4 = MenuEntity(0,"Fanta Fruit Twist","",1, "250ml","","juice",1,1)
        val juice5 = MenuEntity(0,"Pepsi","",1, "250ml","","juice",1,1)
        val juice6 = MenuEntity(0,"Jermuk","",1, "250ml","","juice",1,1)


        val list = mutableListOf(
            pizza1,pizza2,pizza3,pizza4,pizza5,pizza6,
            burger1,burger2,burger3,burger4,burger5,burger6,
            juice1,juice2,juice3,juice4,juice5,juice6)

        DB.getInstance(context).getDao().createMenu(list)
    }

    override fun getMenu() :MutableList<MenuEntity> {
        return  DB.getInstance(context).getDao().getMenudata()

    }

    override fun createOrder() {
        val orderEntity = OrderEntity(1,"00.00.00",false,"kk","",0,0)
        DB.getInstance(context).getDao().createOrder(orderEntity)

    }

    override fun getOrderData(): OrderEntity {
        return DB.getInstance(context).getDao().getOrderData()
    }

    override fun updateOrder(orderEntity: OrderEntity) {
        return DB.getInstance(context).getDao().updateOrder(orderEntity)
    }

    override fun getOrdertotalcount(): Int {
        return DB.getInstance(context).getDao().getOrderData().totalcount
    }

    override fun getOrdertotalprice(): Int {
        return DB.getInstance(context).getDao().getOrderData().totalprice
    }

    override fun updateOrderCountandPrice(id: Int,totalcount: Int,totalprice: Int) {
        DB.getInstance(context).getDao().updateOrderCountandPrice(id,totalcount,totalprice)

    }

    override fun getProductbyid(id : Int): MenuEntity {
        return DB.getInstance(context).getDao().getProductbyid(id)
    }

    override fun isfirstTime(): Boolean {
        return sessionManager.isfirstTime()
    }

    override fun createCart(cartEntity: CartEntity) {
        DB.getInstance(context).getDao().createCartdata(cartEntity)
    }

    override  fun getCart(): MutableList<CartEntity>  {
        return  DB.getInstance(context).getDao().getCartdata()
    }

    override fun getCartdatabyid(id: Int): CartEntity {
        return  DB.getInstance(context).getDao().getCartdatabyid(id)
    }

    override fun deleteCart(id: Int) {
        DB.getInstance(context).getDao().deleteCart(id)
    }

    override fun updateCartcount(id: Int, count: Int) {
        DB.getInstance(context).getDao().updateCartcount(id, count)
    }

    override fun updateCartstate(id: Int, state: Boolean) {
        DB.getInstance(context).getDao().updateCartstate(id,state)
    }

    override fun createSentorder(sentorderEntity: SentorderEntity) {
        DB.getInstance(context).getDao().createSentorder(sentorderEntity)
    }

    override fun deleteSentorder(id: Int) {
        DB.getInstance(context).getDao().deleteSentorder(id)
    }

    override fun getSentosrderdata(): MutableList<SentorderEntity> {
        return DB.getInstance(context).getDao().getSentorderdata()
    }

}

