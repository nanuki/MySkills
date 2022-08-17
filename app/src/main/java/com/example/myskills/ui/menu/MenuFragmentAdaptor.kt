package com.example.myskills.ui.menu


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.myskills.MainViewModel
import com.example.myskills.R
import com.example.myskills.repository.room.MenuEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent

class MenuFragmentAdaptor(val context: Context,
                          val data: MutableList<MenuEntity>,
                          val root: View,
                          val viewModel: MainViewModel
)
    : RecyclerView.Adapter<MenuFragmentAdaptor.CustomHelder>(), KoinComponent {

    val scope = CoroutineScope(Dispatchers.IO)

    var productid = 1
    var therisproduct = true


    class CustomHelder(itemView: View): RecyclerView.ViewHolder(itemView){
        var name : TextView
        var imageView : ImageView

        init {
            name = itemView.findViewById(R.id.name)
            imageView = itemView.findViewById(R.id.image)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHelder {
        val view = LayoutInflater.from(context).inflate(R.layout.menu_,parent,false)

        return CustomHelder(view)
    }

    override fun onBindViewHolder(holder: CustomHelder, position: Int) {
        val currentdata = data[getindexbyposition(position)]
        holder.name.setText(currentdata.name).toString()



        when(currentdata.id ){
            1 -> holder.imageView.setImageResource(R.drawable.with_barbecuechicken_1)
            2 -> holder.imageView.setImageResource(R.drawable.pizza_2)
            3-> holder.imageView.setImageResource(R.drawable.pizza_3)
            4 -> holder.imageView.setImageResource(R.drawable.pizza_4)
            5-> holder.imageView.setImageResource(R.drawable.pizza_5)
            6-> holder.imageView.setImageResource(R.drawable.pizza_6)
            7-> holder.imageView.setImageResource(R.drawable.burger_7)
            8-> holder.imageView.setImageResource(R.drawable.burger_8)
            9-> holder.imageView.setImageResource(R.drawable.burger_9)
            10-> holder.imageView.setImageResource(R.drawable.burger_10)
            11-> holder.imageView.setImageResource(R.drawable.burger_11)
            12-> holder.imageView.setImageResource(R.drawable.burger_12)
            13-> holder.imageView.setImageResource(R.drawable.coca_cola)
            14-> holder.imageView.setImageResource(R.drawable.fanta)
            15-> holder.imageView.setImageResource(R.drawable.sprite)
            16-> holder.imageView.setImageResource(R.drawable.fanta_16)
            17-> holder.imageView.setImageResource(R.drawable.pepsi)
            18-> holder.imageView.setImageResource(R.drawable.jermuk)
        }
        holder.imageView.setOnClickListener {

            productid = currentdata.id
            scope.launch {

                    val list = viewModel.getbusketdata()
                    withContext(Dispatchers.Main){
                        for (item in list){
                            if(item.productid == currentdata.id){
                                productid = item.id
                                therisproduct = false

                            }
                        }
                        val bundle = Bundle()
                        bundle.putInt("id", productid )
                        bundle.putBoolean("therisproduct", therisproduct )

                        Navigation.findNavController(root).navigate(R.id.descriptionFragment,bundle)


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


}
