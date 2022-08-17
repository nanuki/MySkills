package com.example.myskills.ui.menu


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.myskills.MainViewModel
import com.example.myskills.R
import com.example.myskills.databinding.FragmentDescriptionBinding
import com.example.myskills.repository.room.CartEntity
import org.koin.core.component.KoinComponent

class DescriptionFragment : Fragment(), KoinComponent {

    var id_ = 0
    var idproduct = 0
    var countproduct = 0
    var priceproduct = 0
    lateinit var cartEntity : CartEntity
    var thereisnotproduct = true
    var totalcount = 0
    var totalcountcopi = 0
    var totalprice = 0
    var add = true




    private var _binding: FragmentDescriptionBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDescriptionBinding.inflate(inflater, container, false)

        val root: View = binding.root
        val viewmodel: MainViewModel by activityViewModels()

        id_= arguments?.getInt("id") ?: 0
        thereisnotproduct = arguments?.getBoolean("therisproduct") ?: true


        if(!thereisnotproduct){
            viewmodel.getBusketdatabyid(id_)
        }

        else{
            viewmodel.getProduct(id_)
        }



        binding.plus.setOnClickListener {
            add = false

            if (countproduct  == 1){
                if (thereisnotproduct){
                    viewmodel.resetbadge(totalcount+2)
                    viewmodel.resetcount(2)
                    countproduct = 2
                    totalprice = totalprice + (2*priceproduct)
                }
                else{
                    viewmodel.resetbadge(totalcount+1)
                    viewmodel.resetcount(2)
                    countproduct = 2
                    totalprice  = totalprice + priceproduct

                }

            }
            else{
                countproduct = viewmodel.calculatecount(true)
                totalcount = viewmodel.calculatebadge(true)
                totalprice = totalprice + priceproduct
            }



        }

        binding.minus.setOnClickListener {
            add = false

            totalcount = viewmodel.calculatebadge(false)
            countproduct  = viewmodel.calculatecount(false)
            totalprice = totalprice - priceproduct
            if(countproduct == 0){
                if(thereisnotproduct){
                    Navigation.findNavController(binding.root).navigate(R.id.navigation_menu)
                    viewmodel.resetbadge(totalcountcopi)
                }
                else{

                    viewmodel.updateOrderCountandPrice( totalcount,totalprice)
                    viewmodel.deletBusket(idproduct)
                    Navigation.findNavController(binding.root).navigate(R.id.navigation_menu)

                }
            }

        }

        viewmodel.count.observe(viewLifecycleOwner) {
            binding.number.setText("$it")

        }

        viewmodel.busket.observe(viewLifecycleOwner) {

            viewmodel.count.value = it.count
            idproduct = it.id
            priceproduct = it.price
            countproduct = it.count
            binding.image.setImageResource(getImage(it.productid))
            binding.name.setText(it.name)
            binding.price.setText("$priceproduct$")
            binding.description.setText(it.description)
            binding.button.setText(getString(R.string.update_cart))
            binding.number.setText(it.count.toString())
            cartEntity = CartEntity(
                0,
                it.name,
                it.date,
                it.description,
                it.url,
                it.category,
                it.price,
                it.count,
                it.id,
                1
                , false
            )

        }


        viewmodel.product.observe(viewLifecycleOwner) {
            viewmodel.count.value = 1
            priceproduct = it.price
            countproduct = 1
            binding.image.setImageResource(getImage(it.id))
            binding.name.setText(it.name)
            binding.price.setText("$priceproduct$")
            binding.description.setText(it.description)
            binding.number.setText("1")
            binding.button.setText(getString(R.string.add_cart))
            cartEntity = CartEntity(
                0,
                it.name,
                it.date,
                it.description,
                it.url,
                it.category,
                it.price,
                1,
                it.id,
                1,
                false


            )

        }



        viewmodel.order.observe(viewLifecycleOwner) {
            if (it.totalcount == 0) {
                viewmodel.badge.value = 0
                totalcount = 0
                totalprice = 0
                totalcountcopi = totalcount
                // viewmodel.resetbadge(totalcount)

            } else {
                viewmodel.badge.value = it.totalcount
                totalcount = it.totalcount
                totalprice = it.totalprice
                totalcountcopi = totalcount
                //    Log.d("badge", "${viewmodel.badge.value?:0}")

                //  viewmodel.resetbadge(totalcount)
            }

        }

        viewmodel.getOrder()

        binding.button.setOnClickListener {
            //  val sendorderid = viewmodel.getsenderorderid()
            // cartEntity.senderEntity = sendorderid


            totalcount = viewmodel.badge.value?:0
            countproduct = viewmodel.count.value?:0

            if (add){
                if(thereisnotproduct){
                    if (viewmodel.count.value == 1){
                        totalcount++
                        totalprice  += priceproduct
                    }

                }


            }

            if (thereisnotproduct){
                cartEntity.count = countproduct
                viewmodel.creatBuscet(cartEntity)

                Navigation.findNavController(binding.root).navigate(R.id.navigation_menu)




            }
            else{
                viewmodel.updateBusketcount(idproduct ,countproduct)


                Navigation.findNavController(binding.root).navigate(R.id.navigation_menu)


            }
            viewmodel.updateOrderCountandPrice( totalcount,totalprice)
            viewmodel.resetbadge(totalcount)


        }


        return root
    }

    fun getImage(id: Int):Int{
        var find = 0
        if (id == 1){ find = R.drawable.with_barbecuechicken_1
        }
        else if (id == 2){ find = R.drawable.pizza_2
        }
        else if (id == 3){ find = R.drawable.pizza_3
        }
        else if (id == 4){ find = R.drawable.pizza_4
        }
        else if (id == 5){ find = R.drawable.pizza_5
        }
        else if (id == 6){ find = R.drawable.pizza_6
        }
        else if (id == 7){ find = R.drawable.burger_7
        }
        else if (id == 8){ find = R.drawable.burger_8
        }
        else if (id == 9){ find = R.drawable.burger_9
        }
        else if (id == 10){ find = R.drawable.burger_10
        }
        else if (id == 11){ find = R.drawable.burger_11
        }
        else if (id == 12){ find = R.drawable.burger_12
        }
        else if (id == 13){ find = R.drawable.coca_cola
        }
        else if (id == 14){ find = R.drawable.fanta
        }
        else if (id == 15){ find = R.drawable.sprite
        }
        else if (id == 16){ find = R.drawable.fanta_16
        }
        else if (id == 17){ find = R.drawable.pepsi
        }
        else if (id == 18){ find = R.drawable.jermuk
        }

        return find

    }


}