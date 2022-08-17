package com.example.myskills.ui.order

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
import com.example.myskills.repository.room.SentorderEntity
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent

class SentOrderAdapter(
    val context: Context,
    val data: MutableList<SentorderEntity>,
    var orderViewModel: MainViewModel
): RecyclerView.Adapter<SentOrderAdapter.CustomHelder>(), KoinComponent {

    val scope = CoroutineScope(Dispatchers.IO)

    class CustomHelder(itemView: View): RecyclerView.ViewHolder(itemView){
        var address : TextView
        var date : TextView
        var totalprice : TextView
        var totalcount : TextView
        var phone: TextView
        var imageView: ImageView

        init {
            address = itemView.findViewById(R.id.address_)
            date = itemView.findViewById(R.id.date)
            totalprice = itemView.findViewById(R.id.total_price)
            totalcount = itemView.findViewById(R.id.total_count)
            phone = itemView.findViewById(R.id.phone)
            imageView = itemView.findViewById(R.id.imageView)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHelder {
        val view = LayoutInflater.from(context).inflate(R.layout.order_row,parent,false)

        return CustomHelder(view)
    }

    override fun onBindViewHolder(holder: CustomHelder, position: Int) {
        val currentdata = data[getindexbyposition(position)]
        holder.address.setText(currentdata.adres).toString()
        holder.date.setText(currentdata.date).toString()
        holder.phone.setText(currentdata.phone_number).toString()
        val count = currentdata.totalcount
        val price = currentdata.totalprice
        val totalprice = "PRICE($price$)"
        val totalcount = "ITEMS ($count)"
        holder.totalprice.setText(totalprice).toString()
        holder.totalcount.setText(totalcount).toString()


        holder.imageView.setOnClickListener {
            scope.launch {
                orderViewModel.deletesentorder(currentdata.id)
                withContext(Dispatchers.Main){
                    deleteItem(getindexbyposition(position))
                    delay(300)
                    orderViewModel.getsentOrderData()

                }
            }
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun getindexbyposition(position : Int): Int{
        return position % data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleteItem(position: Int){
        data.removeAt(position)
        notifyDataSetChanged()
    }

}