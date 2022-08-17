package com.example.myskills

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myskills.repository.room.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel:  ViewModel(), KoinComponent {
    private val repo : RoomRepository by inject()
    private var k = 0



    private val _listMenuEntity = MutableLiveData<MutableList<MenuEntity>>()
    var listMenuEntity : MutableLiveData<MutableList<MenuEntity>> = _listMenuEntity

    private val _listMenuBusket = MutableLiveData<MutableList<CartEntity>>()
    var listMenuBusket : MutableLiveData<MutableList<CartEntity>> = _listMenuBusket

    private val _cart= MutableLiveData<CartEntity>()
    var cart : MutableLiveData<CartEntity> = _cart

    private val _listSentorder = MutableLiveData<MutableList<SentorderEntity>>()
    var listSentorder : MutableLiveData<MutableList<SentorderEntity>> = _listSentorder


    private val _order= MutableLiveData<OrderEntity>()
    var order : MutableLiveData<OrderEntity> = _order

    private val _product = MutableLiveData<MenuEntity>()
    var product: MutableLiveData<MenuEntity> = _product

    private val _count = MutableLiveData<Int>()
    var count: MutableLiveData<Int> = _count

    private val _totalcount = MutableLiveData<Int>()
    var totalcount: MutableLiveData<Int> = _totalcount

    private val _badge = MutableLiveData<Int>()
    var badge: MutableLiveData<Int> = _badge

    private val _totalprice = MutableLiveData<Int>()
    var totalprice : MutableLiveData<Int> = _totalprice




    fun getProduct(id:Int){
        viewModelScope.launch(Dispatchers.IO){
            product.postValue( repo.getProductbyid(id))
        }
    }

    fun createCart(cartEntity: CartEntity){
        viewModelScope.launch(Dispatchers.IO){
            repo.createCart(cartEntity)
        }
    }

    fun deleteCart(id: Int){
        viewModelScope.launch (Dispatchers.IO) {
            repo.deleteCart(id)
        }
    }

    fun updateCartcount(id: Int, count : Int){
        viewModelScope.launch (Dispatchers.IO) {
            repo.updateCartcount(id,count)
        }

    }

    fun getCartdatabyid(id:Int){
        viewModelScope.launch (Dispatchers.IO){
            cart.postValue(repo.getCartdatabyid(id))
        }

    }

    fun getCartdata(): MutableList<CartEntity>{
        return repo.getCart()
    }

    fun getCart(){
        viewModelScope.launch(Dispatchers.IO){
            listMenuBusket.postValue(repo.getCart())
        }
    }


    fun createOrder(){
        viewModelScope.launch(Dispatchers.IO){
            repo.createOrder()
        }
    }


    fun getOrder(){
        viewModelScope.launch(Dispatchers.IO){
            order.postValue(repo.getOrderData())
        }
    }





    fun updateOrder(orderEntity: OrderEntity){
        viewModelScope.launch(Dispatchers.IO){
            repo.updateOrder(orderEntity)
        }
    }
    fun updateOrderCountandPrice(totalcount :Int, totalPrice :Int){
        viewModelScope.launch(Dispatchers.IO){
            repo.updateOrderCountandPrice(1, totalcount,totalPrice)
        }
    }
    fun createMenu(){
        viewModelScope.launch(Dispatchers.IO){
            repo.createMenu()
        }
    }
    fun showmenu1(){
        viewModelScope.launch(Dispatchers.IO){
            listMenuEntity.postValue(repo.getMenu())
        }
    }


    fun isfirstTime():Boolean{
        return repo.isfirstTime()    }


    fun calculatecount(b: Boolean): Int {
        var y = count.value?: 0
        if (b){ y++ }
        else { y-- }
        count.postValue(y)
        return y
    }

    fun resetcount(countproduct: Int) {
        count.postValue(countproduct)
    }

    fun resetbadge(number: Int) {
        badge.postValue(number)
    }

    fun getbedgenumber(): Int{
        var y = 0
        viewModelScope.launch(Dispatchers.IO){
            k = repo.getOrderData().totalcount
            withContext(Dispatchers.Main){
                y  = badge.value?:k

                badge.postValue(y)
            }
        }

        return y
    }

    fun calculatebadge(b: Boolean): Int{

        var y :Int = badge.value?:0
        if (b){ y++ }
        else {
            if (y <= 0){
                y = 0
            }
            else{ y-- }
        }
        badge.postValue(y)

        return y

    }
    fun calculatetotalprice(b: Boolean, price: Int): Int{

        var y :Int = this.totalprice.value?:0
        if (b){ y += price }
        else {
            if (y <= 0){
                y = 0
            }
            else{ y -= price }
        }
        this.totalprice.postValue(y)

        return y

    }

    fun resettotalprice(number: Int) {
        totalprice.postValue(number)
    }




    fun createSentorder(sentorderEntity: SentorderEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repo.createSentorder(sentorderEntity)
        }
    }
    fun deleteSentorder(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteSentorder(id)
        }
    }
    fun getsentOrderData(){
        viewModelScope.launch(Dispatchers.IO) {
            listSentorder.postValue(repo.getSentosrderdata())
        }

    }

}