package com.example.myskills.ui.cart

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myskills.MainViewModel
import com.example.myskills.R
import com.example.myskills.repository.room.CartEntity
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent

class CartAdaptor(
    val context: Context,
    private val data: MutableList<CartEntity>,
    private val viewModel: MainViewModel
) : RecyclerView.Adapter<CartAdaptor.CustomHelder>(),
    KoinComponent {


    private val scope = CoroutineScope(Dispatchers.IO)
    private var countproduct = 0
    private var priceproduct = 0


    class CustomHelder(itemView: View): RecyclerView.ViewHolder(itemView){
        var name : TextView
        var image : ImageView
        var minus : TextView
        var plus : TextView
        var cunttextview : TextView
        var pricetextview : TextView
        var discription : TextView

        init {
            name = itemView.findViewById(R.id.name_)
            image = itemView.findViewById(R.id.image)
            minus = itemView.findViewById(R.id.minus)
            plus = itemView.findViewById(R.id.plus)
            cunttextview = itemView.findViewById(R.id.number)
            pricetextview = itemView.findViewById(R.id.priceitem)
            discription = itemView.findViewById(R.id.description_)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHelder {
        val view = LayoutInflater.from(context).inflate(R.layout.cart_row,parent,false)
        return CustomHelder(view)
    }

    override fun onBindViewHolder(holder: CustomHelder, position: Int) {
        val currentdata = data[getindexbyposition(position)]

        holder.name.text = currentdata.name
        val price = currentdata.count * currentdata.price
        holder.pricetextview.text = price.toString()
        holder.cunttextview.text = currentdata.count.toString()
        holder.discription.text = currentdata.description
        holder.image.setImageResource( getimage(currentdata.productid))

        holder.plus.setOnClickListener {
            priceproduct = currentdata.price
            inccountandprice(true,holder.cunttextview,holder.pricetextview,currentdata.id,position,currentdata.price)

        }
        holder.minus.setOnClickListener {
            priceproduct = currentdata.price
            inccountandprice(false, holder.cunttextview, holder.pricetextview, currentdata.id, position, currentdata.price)

        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun getindexbyposition(position : Int): Int{
        return position % data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleteItem(position: Int){
        data.removeAt(position)
        notifyDataSetChanged()
    }

    private fun getimage(id: Int): Int{
        var image = 0
        when(id){
            1 -> image = R.drawable.with_barbecuechicken_1
            2 -> image = R.drawable.pizza_2
            3->  image = R.drawable.pizza_3
            4 -> image = R.drawable.pizza_4
            5->  image = R.drawable.pizza_5
            6->  image = R.drawable.pizza_6
            7->  image = R.drawable.burger_7
            8->  image = R.drawable.burger_8
            9->  image = R.drawable.burger_9
            10-> image = R.drawable.burger_10
            11-> image = R.drawable.burger_11
            12-> image = R.drawable.burger_12
            13-> image = R.drawable.coca_cola
            14-> image = R.drawable.fanta
            15-> image = R.drawable.sprite
            16-> image = R.drawable.fanta_16
            17-> image = R.drawable.pepsi
            18-> image = R.drawable.jermuk
        }
        return image
    }



    private fun inccountandprice(x: Boolean, counttextview: TextView, pricetextview: TextView,
                                 id: Int, position: Int, price: Int)
    {
        var totalprice = 0
        var totalcount= 0

        countproduct = counttextview.text.toString().toInt()

        for (item in data) {
            totalcount += item.count
            totalprice += (item.count * item.price)

        }

        if (x) {
            countproduct++
            counttextview.text = "$countproduct"
            pricetextview.text = "${countproduct*price}"
            totalcount = viewModel.calculatebadge(true)
            totalprice = viewModel.calculatetotalprice(true,price)

            scope.launch{
                viewModel.updateOrderCountandPrice(totalcount,totalprice)
                viewModel.updateBusketcount(id,countproduct)
            }


        }
        else {
            countproduct--
            counttextview.text = "$countproduct"
            pricetextview.text = "${countproduct*price}"
            totalcount = viewModel.calculatebadge(false)
            totalprice = viewModel.calculatetotalprice(false,price)


            if (countproduct == 0) {
                scope.launch {
                    viewModel.updateOrderCountandPrice(totalcount, totalprice )
                    viewModel.deletBusket(id)
                    withContext(Dispatchers.Main){
                        deleteItem(getindexbyposition(position))
                    }
                    delay(100)
                    viewModel.getOrder()
                    viewModel.getBuscet()
                }

            }

            else{
                scope.launch{
                    viewModel.updateOrderCountandPrice(totalcount, totalprice)
                    viewModel.updateBusketcount(id,countproduct)

                }


            }
        }


    }

}